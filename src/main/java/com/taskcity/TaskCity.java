package com.taskcity;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.taskcity.security.Credentials;
import com.taskcity.security.EmailAuth;

public class TaskCity {
	private static TaskCity instance;

	private Credentials creds;

	private TaskCity() {
		creds = Credentials.getInstance();
	}

	public static TaskCity getInstance() {
		if (instance == null) {
			instance = new TaskCity();
		}

		return instance;
	}

	public boolean sendEmail(String subject, String body) {
		String[] emailProps = creds.getEmailProperties()
				.split("\\|");
		Properties props = new Properties();
		props.put("mail.smtp.host", emailProps[0]);
		props.put("mail.smtp.socketFactory.port", emailProps[1]);
		props.put("mail.smtp.socketFactory.class", emailProps[2]);
		props.put("mail.smtp.auth", emailProps[3]);
		props.put("mail.smtp.port", emailProps[4]);
		Session session = Session.getDefaultInstance(props, new EmailAuth());

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(creds.getEmailFrom()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(creds.getEmailTo()));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (MessagingException e) {
			Logger.log("Error sending email", e);
			return false;
		}

		return true;
	}
}
