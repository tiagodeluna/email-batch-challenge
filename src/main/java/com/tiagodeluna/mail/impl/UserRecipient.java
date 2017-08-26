package com.tiagodeluna.mail.impl;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.mail.EmailRecipient;
import com.tiagodeluna.mail.EmailService;

/**
 * Uses the idea of Decorator pattern to combine the features {@link User} and 
 * {@link EmailRecipient} functionalities, adding the {@link EmailService.MailType}.
 * 
 * @author Tiago Luna
 *
 */
public class UserRecipient extends User implements EmailRecipient {

	private EmailService.MailType mailType;
	
	public UserRecipient(User user, EmailService.MailType mailType) {
		super(user.getEmail(), user.hasContract(), user.getFriendsNumber(), user.getSentInvitationsNumber());
		this.mailType = mailType;
	}

	public EmailService.MailType getMailType() {
		return mailType;
	}

	public void setMailType(EmailService.MailType mailType) {
		this.mailType = mailType;
	}
}
