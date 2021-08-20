package ISA.Team22.Domain.Users;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Person implements UserDetails, Serializable{
	
	@Id
	@SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password",  nullable = false)
	private String password;
	
	//@Column(name = "username",  nullable = false)
	//private String username;
	
	@Column(name = "name",  nullable = false)
	private String name;
	
	@Column(name = "lastName",  nullable = false)
	private String lastName;
	
	@Column(name = "contact")
	private String contact;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
	private Address address;
	
    @Column(name = "last_password_reset_date")
	private Timestamp lastPasswordResetDate;
    
	@Column(name = "enabled")
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;
	
	@Column(name = "firstLogged", nullable = true)
    private Boolean firstLogged;
	   
	public Person() {
		super();
	}

	public Person(Long id, String email, String password, String name, String lastName, String contact, Address address,
			Timestamp lastPasswordResetDate, boolean enabled, List<Authority> authorities, Boolean firstLogged) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.contact = contact;
		this.address = address;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.enabled = enabled;
		this.authorities = authorities;
		this.firstLogged = firstLogged;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		 Timestamp now = new Timestamp(new Date().getTime());
	     this.setLastPasswordResetDate(now);
	     this.password = password;
	}
	

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
	

	public Boolean getFirstLogged() {
		return firstLogged;
	}

	public void setFirstLogged(Boolean firstLogged) {
		this.firstLogged = firstLogged;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	  
	 @JsonIgnore
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return this.authorities;
	    }

	    @Override
	    public boolean isEnabled() {
	        return enabled;
	    }
	
}
