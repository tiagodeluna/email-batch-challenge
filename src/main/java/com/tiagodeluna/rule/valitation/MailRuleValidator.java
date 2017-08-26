package com.tiagodeluna.rule.valitation;

import java.util.Collections;
import java.util.List;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.rule.MailRule;

/**
 * Class that processes the email sending rules for a provided {@link User}, 
 * returning the {@link MailRule} with most priority.
 * 
 * @author Tiago Luna
 *
 */
public class MailRuleValidator {

	private List<MailRule> rules;
	
	public MailRuleValidator(MailRuleLoader loader) {
		//Loads the list of rules
		this.rules = loader.load();
		//Sorts list
		this.sortRules();
	}
	
	/**
	 * Processes rules for a {@link User} and returns the {@link MailRule} with most priority.
	 * 
	 * @param user A User to be verified.
	 * @return A {@link MailRule} or {@value null} if no rule is satisfied.
	 */
	public MailRule validate(User user) {
		for (MailRule rule : this.rules) {
			if (rule.isSatisfied(user)) {
				return rule;
			}
		}

		return null;
	}
	
	/**
	 * Sorts the list to ensure that the first match is the one of the highest priority rule.
	 */
	private void sortRules() {
		Collections.sort(this.rules, Collections.reverseOrder());
	}
	
}
