package com.tiagodeluna.rule.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.tiagodeluna.rule.Condition;
import com.tiagodeluna.rule.MailRule;
import com.tiagodeluna.rule.SignType;
import com.tiagodeluna.rule.valitation.MailRuleLoader;
import com.tiagodeluna.rule.valitation.MailRuleValidator;

/**
 * 
 * @author Tiago Luna
 *
 */
@RunWith(EasyMockRunner.class)
public class MailRuleValidatorTest {

	private MailRuleValidator validator;
	
	private static final EmailService.MailType LOWER_PRIORITY = EmailService.MailType.MAIL_TYPE_1;
	private static final EmailService.MailType HIGHER_PRIORITY = EmailService.MailType.MAIL_TYPE_5;
	
	@Mock
	private MailRuleLoader loader;
	
	@Before
	public void setUp() {
		EasyMock.expect(loader.load()).andReturn(getRules());
		EasyMock.replay(loader);
		
		this.validator = new MailRuleValidator(this.loader);
	}
	
	@Test
	public void testNoRuleSatisfied() {
		User user = new User("user@mail.com", false, 0, 0);
		MailRule result = validator.validate(user);
		
		assertNull(result);
	}
	
	@Test
	public void testSingleRuleSatisfied() {
		User user = new User("user@mail.com", true, 4, 0);
		MailRule result = validator.validate(user);
		
		assertNotNull(result);
		assertEquals(LOWER_PRIORITY, result.getMailType());
	}
	
	@Test
	public void testMoreThanOneRuleSatisfied() {
		User user = new User("user@mail.com", true, 4, 2);
		MailRule result = validator.validate(user);
		
		assertNotNull(result);
		assertEquals(HIGHER_PRIORITY, result.getMailType());
	}
	
	private List<MailRule> getRules() {
		List<MailRule> rules = new ArrayList<>();
		
		MailRule rule1 = new MailRule(true,
				new Condition(SignType.EQUALS, 4),
				new Condition(SignType.LESS_THAN, 4),
				LOWER_PRIORITY);
		rules.add(rule1);

		MailRule rule2 = new MailRule(true,
				new Condition(SignType.GREATER_THAN, 3),
				new Condition(SignType.GREATER_THAN, 1),
				HIGHER_PRIORITY);
		rules.add(rule2);

		return rules;
	}
}
