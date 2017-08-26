package com.tiagodeluna.processing.impl;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.mail.EmailService;
import com.tiagodeluna.mail.impl.UserRecipient;
import com.tiagodeluna.processing.impl.EmailWriter;
import com.tiagodeluna.processing.impl.UserProcessing;
import com.tiagodeluna.processing.impl.UserReader;
import com.tiagodeluna.rule.Condition;
import com.tiagodeluna.rule.MailRule;
import com.tiagodeluna.rule.SignType;
import com.tiagodeluna.rule.valitation.MailRuleLoader;

@RunWith(EasyMockRunner.class)
public class UserProcessingTest {

	private UserProcessing userProcessing;
	
	@Mock
	private EmailService emailService;
	@Mock
	private MailRuleLoader mailRuleLoader;
	
	private User user;
	
	@Before
	public void setUp() {
		this.user = new User("user@mail.com", true, 1, 1);
		
		List<User> items = new ArrayList<>();
		items.add(user);
		
		List<MailRule> rules = new ArrayList<>();
		rules.add(new MailRule(true,
				new Condition(SignType.GREATER_THAN, 0),
				new Condition(SignType.GREATER_THAN, 0),
				EmailService.MailType.MAIL_TYPE_2));
		
		EasyMock.expect(this.mailRuleLoader.load()).andReturn(rules);
		EasyMock.replay(this.mailRuleLoader);
		
		this.userProcessing = new UserProcessing(
				new UserReader(items),
				new EmailWriter(this.emailService),
				this.mailRuleLoader);
	}
	
	@Test
	public void testDoProcessingWithEmailSending() {
		this.emailService.sendMail(EasyMock.anyObject(UserRecipient.class),
				EasyMock.anyObject(EmailService.MailType.class));
		EasyMock.expectLastCall();
		
		EasyMock.replay(this.emailService);
		
		this.userProcessing.doProcessing();
	}
	
}
