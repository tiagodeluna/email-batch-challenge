package com.tiagodeluna.processing.impl;

import com.tiagodeluna.backend.User;
import com.tiagodeluna.mail.impl.UserRecipient;
import com.tiagodeluna.processing.ItemProcessing;
import com.tiagodeluna.processing.ItemReader;
import com.tiagodeluna.processing.ItemWriter;
import com.tiagodeluna.rule.MailRule;
import com.tiagodeluna.rule.valitation.MailRuleLoader;
import com.tiagodeluna.rule.valitation.MailRuleValidator;

/**
 * Class that implements {@link ItemProcessing} for {@link User} and {@link UserRecipient}.
 * 
 * @author Tiago Luna
 *
 */
public class UserProcessing extends ItemProcessing<User, UserRecipient> {

	private MailRuleValidator validator;
	
	public UserProcessing(ItemReader<User> reader, ItemWriter<UserRecipient> writer, MailRuleLoader loader) {
		super(reader, writer);
		this.validator = new MailRuleValidator(loader);
	}

	@Override
	protected UserRecipient process(User item) {
		//Verify if the user status satisfies one of the rules
		MailRule rule = this.validator.validate(item);
		
		//Prepare user recipient
		if (rule != null) {
			return new UserRecipient(item, rule.getMailType());
		}
		return null;
	}


}
