package com.tiagodeluna.main;

import com.tiagodeluna.backend.persistence.UserDAO;
import com.tiagodeluna.mail.impl.SimpleEmailService;
import com.tiagodeluna.processing.impl.EmailWriter;
import com.tiagodeluna.processing.impl.UserProcessing;
import com.tiagodeluna.processing.impl.UserReader;
import com.tiagodeluna.rule.valitation.MailRuleLoader;

/**
 * Execute this class to test the application. 
 * 
 * @author Tiago Luna
 *
 */
public class Main {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		UserReader reader = new UserReader(dao.findAll());
		SimpleEmailService service = new SimpleEmailService();
		EmailWriter writer = new EmailWriter(service);
		MailRuleLoader ruleLoader = new MailRuleLoader();
		
		UserProcessing processing = new UserProcessing(reader, writer, ruleLoader);
		
		processing.doProcessing();
	}
}
