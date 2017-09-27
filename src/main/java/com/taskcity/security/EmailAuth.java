package com.taskcity.security;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuth extends Authenticator {
	private static Credentials creds = Credentials.getInstance();

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(creds.getEmailFrom(), creds.getEmailPassword());
	}
}
