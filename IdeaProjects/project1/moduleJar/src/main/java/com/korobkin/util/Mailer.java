package com.korobkin.util;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Util class that send e-mail message
 */
public class Mailer {
	private static final Logger logger = Logger.getLogger(Mailer.class);
	Session session; // session
	Properties properties; // properties
	Transport transport; // transport
	MimeMessage message; // message


	public Mailer() {
		properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		session = Session.getDefaultInstance(properties, null);

		message = new MimeMessage(session);
	}

	/**
	 * Sending e-mail.
	 * @param toEmail e-mail address
	 * @param subject title of message
	 * @param htmlBody body of message
	 */
	public void sendEmail(String toEmail, String subject, String htmlBody)
			throws MessagingException {

		/* set parameters for connection to gmail SMTP */
		String fromUser = "carrentforepam@gmail.com";
		String fromUserEmailPassword = "carrent2016";
		String emailHost = "smtp.gmail.com";
		/* get parameters for message from message.properties */


		InternetHeaders headers = new InternetHeaders();
		headers.addHeader("Content-type", "text/html; charset=UTF-8");
		try {
			MimeBodyPart body = new MimeBodyPart(headers, htmlBody.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}


		message.setSubject(subject);
		message.setFrom(new InternetAddress("support@renter.com"));
		message.setText(htmlBody, "utf-8", "html");

		message.addRecipient(javax.mail.Message.RecipientType.TO, 
                new InternetAddress(toEmail));


		/* create transport and send prepared message */
		transport = session.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	
	}
}
