package com.tiagodeluna.rule;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.mail.EmailService;

/**
 * Defines a rule for sending e-mail to a {@link User}, with up to three {@link Condition}.
 * 
 * @author Tiago Luna
 */
public class MailRule implements Comparable<MailRule> {

    private boolean hasContract;
    private Condition friendsNumber;
    private Condition invitationsNumber;
//	private int priority;
    private EmailService.MailType mailType;
	
	public MailRule(boolean hasContract, Condition friendsNumber, Condition invitationsNumber, 
			EmailService.MailType mailType) {
		super();
		this.hasContract = hasContract;
		this.friendsNumber = friendsNumber;
		this.invitationsNumber = invitationsNumber;
		this.mailType = mailType;
	}

	/**
	 * Responsible for verifying if a {@link User} satisfies the rule, with all its {@link Condition}.
	 * 
	 * @param user A User to be verified.
	 * @return The result of the check.
	 */
	public boolean isSatisfied(User user) {
		return this.hasContract == user.hasContract()
			&& (friendsNumber == null || friendsNumber.isSatisfied(user.getFriendsNumber()))
			&& (invitationsNumber == null || invitationsNumber.isSatisfied(user.getSentInvitationsNumber()));
	}

	public boolean isHasContract() {
		return hasContract;
	}

	public void setHasContract(boolean hasContract) {
		this.hasContract = hasContract;
	}

	public Condition getFriendsNumber() {
		return friendsNumber;
	}

	public void setFriendsNumber(Condition friendsNumber) {
		this.friendsNumber = friendsNumber;
	}

	public Condition getInvitationsNumber() {
		return invitationsNumber;
	}

	public void setInvitationsNumber(Condition invitationsNumber) {
		this.invitationsNumber = invitationsNumber;
	}

	public EmailService.MailType getMailType() {
		return mailType;
	}

	public void setMailType(EmailService.MailType mailType) {
		this.mailType = mailType;
	}

	public int compareTo(MailRule o) {
		return this.mailType.compareTo(o.mailType);
	}
	
}