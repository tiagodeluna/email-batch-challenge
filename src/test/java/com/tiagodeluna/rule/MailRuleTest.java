package com.tiagodeluna.rule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.mail.EmailService;
import com.tiagodeluna.rule.Condition;
import com.tiagodeluna.rule.MailRule;
import com.tiagodeluna.rule.SignType;

/**
 * 
 * @author Tiago Luna
 *
 */
public class MailRuleTest {

	private MailRule rule;

	@Before
	public void setUp() {
		this.rule = new MailRule(true,
				new Condition(SignType.GREATER_THAN, 0),
				new Condition(SignType.GREATER_THAN, 0),
				EmailService.MailType.MAIL_TYPE_2);
	}

	@Test
	public void testRuleSatisfied() {
		User user = new User("user@mail.com", true, 1, 1);

		assertEquals(true, this.rule.isSatisfied(user));
	}
	
	@Test
	public void testRuleUnsatisfiedHasContractCondition() {
		User user = new User("user@mail.com", false, 1, 1);

		assertEquals(false, this.rule.isSatisfied(user));
		assertNotEquals(this.rule.isHasContract(), user.hasContract());
	}
	
	@Test
	public void testRuleUnsatisfiedFriendsCondition() {
		User user = new User("user@mail.com", true, 0, 1);

		assertEquals(false, this.rule.isSatisfied(user));
		assertEquals(this.rule.isHasContract(), user.hasContract());
		assertEquals(false, user.getFriendsNumber() > this.rule.getFriendsNumber().getValue());
	}

	@Test
	public void testRuleUnsatisfiedInvitationsCondition() {
		User user = new User("user@mail.com", true, 1, 0);

		assertEquals(false, this.rule.isSatisfied(user));
		assertEquals(this.rule.isHasContract(), user.hasContract());
		assertEquals(true, user.getFriendsNumber() > this.rule.getFriendsNumber().getValue());
		assertEquals(false, user.getSentInvitationsNumber() > this.rule.getInvitationsNumber().getValue());
	}
	
	@Test
	public void testRuleSatisfiedNonExistentFriendsCondition() {
		this.rule.setFriendsNumber(null);
		User user = new User("user@mail.com", true, 0, 1);

		assertEquals(true, this.rule.isSatisfied(user));
	}

	@Test
	public void testRuleSatisfiedNoExistentInvitationsCondition() {
		this.rule.setInvitationsNumber(null);
		User user = new User("user@mail.com", true, 1, 0);

		assertEquals(true, this.rule.isSatisfied(user));
	}

	@Test
	public void testCompareToHigherPriorityRule() {
		MailRule higherPriorityRule = new MailRule(true,
				null, null, EmailService.MailType.MAIL_TYPE_4);
		
		assertEquals(true, this.rule.compareTo(higherPriorityRule) < 0);
	}

}
