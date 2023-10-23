package br.com.api.ecommerce.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	  @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl disparoEmail = new JavaMailSenderImpl();
	        disparoEmail.setHost("smtp.gmail.com");
	        disparoEmail.setPort(587);

	        disparoEmail.setUsername("grupo6api2023@gmail.com");
	        disparoEmail.setPassword("Apirest2023!");

	        Properties props = disparoEmail.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");

	        return disparoEmail;
	  }
}