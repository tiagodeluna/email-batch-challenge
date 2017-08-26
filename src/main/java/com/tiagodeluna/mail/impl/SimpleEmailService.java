package com.tiagodeluna.mail.impl;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tiagodeluna.mail.EmailRecipient;
import com.tiagodeluna.mail.EmailService;
import com.tiagodeluna.util.MailProperties;

public class SimpleEmailService implements EmailService {

	private static final String MESSAGE_FORMAT = "*** NEW EMAIL ***\nRecipient: %s\nMessage: %s";

	private final Logger LOGGER = Logger.getLogger(SimpleEmailService.class.getName());
	
	private Map<String, String> templates;
	
	public SimpleEmailService() {
		try {
			this.templates = MailProperties.load(MailProperties.EMAIL_TEMPLATES);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	@Override
	public void sendMail(EmailRecipient recipient, MailType mailType) {
		LOGGER.log(Level.FINE, "Sending a new email");
		System.out.println(String.format(MESSAGE_FORMAT, 
				recipient.getEmail(),
				templates.get(mailType.name())));
	}

}
