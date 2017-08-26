package com.tiagodeluna.rule.valitation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tiagodeluna.mail.EmailService.MailType;
import com.tiagodeluna.rule.Condition;
import com.tiagodeluna.rule.MailRule;
import com.tiagodeluna.rule.SignType;
import com.tiagodeluna.util.MailProperties;

/**
 * 
 * @author Tiago Luna
 *
 */
public class MailRuleLoader {
	
	private final Logger LOGGER = Logger.getLogger(MailRuleLoader.class.getName());
	
	public MailRuleLoader() {
		super();
	}
	
	public List<MailRule> load() {
		List<MailRule> rules = new ArrayList<>();

		try {
			Map<String, String> entries = MailProperties.load(MailProperties.EMAIL_RULES);
			
			//Build the list of rules
			for (String entry : entries.values()) {
				rules.add(
						getRule(entry.split(MailProperties.SEPARATOR)));
			}
			
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		
		return rules;
	}
	
	private MailRule getRule(String[] codes) {
		return new MailRule(Integer.parseInt(codes[0]) == 1,
				getCondition(codes[1], codes[2]),
				getCondition(codes[3], codes[4]),
				MailType.values()[Integer.parseInt(codes[5])-1]);
	}
	
	private Condition getCondition(String sign, String value) {
		Condition condition = null;
		if (!sign.isEmpty() && !value.isEmpty()) {
			condition = new Condition(SignType.fromValue(sign), Integer.parseInt(value));
		}
		return condition;
	}
}
