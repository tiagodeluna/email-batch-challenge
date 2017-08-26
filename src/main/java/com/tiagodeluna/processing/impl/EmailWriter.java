package com.tiagodeluna.processing.impl;

import com.tiagodeluna.mail.EmailService;
import com.tiagodeluna.mail.impl.UserRecipient;
import com.tiagodeluna.processing.ItemWriter;

/**
 * 
 * @author Tiago Luna
 *
 */
public class EmailWriter implements ItemWriter<UserRecipient> {

	private EmailService service;
	
	public EmailWriter(EmailService service) {
		this.service = service;
	}
	
	/**
	 * Call EmailService passing data.
	 */
	public void write(UserRecipient item) {
		this.service.sendMail(item, item.getMailType());
	}
	
}
