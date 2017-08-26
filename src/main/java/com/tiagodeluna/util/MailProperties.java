package com.tiagodeluna.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * Responsible for reading a properties file and returns a Map of entries.
 * 
 * @author Tiago Luna
 *
 */
public final class MailProperties {

	public static final String EMAIL_TEMPLATES = "email_templates.xml";
	public static final String EMAIL_RULES = "email_rules.xml";
	public static final String SEPARATOR = "-";

	/**
	 * Good Practice: Private constructor added to hide the implicit public one.
	 */
	private MailProperties() {
		super();
	}

	/**
	 * Read the properties file and returns all entries as a Map.
	 * 
	 * @return A Map of entries.
	 * @throws IOException
	 */
	public static Map<String, String> load(String fileName) throws IOException {
		Properties prop = new Properties();

		ClassLoader classLoader = MailProperties.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		prop.loadFromXML(new FileInputStream(file));
		
		Map<String, String> map = new HashMap<>();
		
		for (Entry<Object, Object> entry : prop.entrySet()) {
			map.put((String)entry.getKey(), (String)entry.getValue());
		}
		
		return map;
	}
}
