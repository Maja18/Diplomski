package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender javaMailSender;
	private Environment environment;

	@Autowired
	public EmailService(JavaMailSender javaMailSender, Environment environment) {
		this.javaMailSender = javaMailSender;
		this.environment = environment;
	}

	@Async
	public void sendNotificationAsync(String sendTo, String subject, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(sendTo);
		mail.setFrom(environment.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(message);
		try {
			javaMailSender.send(mail);
			System.out.println("Poslat je email");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
