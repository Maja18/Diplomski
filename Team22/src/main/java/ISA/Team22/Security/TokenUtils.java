package ISA.Team22.Security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ISA.Team22.Domain.Users.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


//Utility klasa za rad sa JSON Web Tokenima
@Component
public class TokenUtils {
	@Value("isabackend")
	private String APP_NAME;

	@Value("somesecret")
	public String SECRET;

	@Value("86400000")
	private int EXPIRES_IN;

	@Value("Authorization")
	private String AUTH_HEADER;
	

    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

	
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setIssuer(APP_NAME)
				.setSubject(email)
				.setAudience(generateAudience())
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
	}
	
	private String generateAudience() {
		return "web";
	}
	
	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}
	
	// Funkcija za refresh JWT tokena
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getIssuedAtDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }
	
	// Funkcija za validaciju JWT tokena
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getEmailFromToken(token);
		
		return (email != null && email.equals(userDetails.getUsername()));
	}

	public String getEmailFromToken(String token) {
		String email;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			email = claims.getSubject();
		} catch (Exception e) {
			email = null;
		}
		return email;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}
	
	 private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
	        return (lastPasswordReset != null && created.before(lastPasswordReset));
	    }

	    private Boolean isTokenExpired(String token) {
	        final Date expiration = this.getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }

	    private Boolean ignoreTokenExpiration(String token) {
	        String audience = this.getAudienceFromToken(token);
	        return (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));
	    }
	    
	    public String getAudienceFromToken(String token) {
	        String audience;
	        try {
	            final Claims claims = this.getAllClaimsFromToken(token);
	            audience = claims.getAudience();
	        } catch (Exception e) {
	            audience = null;
	        }
	        return audience;
	    }


	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public int getExpiredIn() {
		return EXPIRES_IN;
	}

	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}

	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
}
