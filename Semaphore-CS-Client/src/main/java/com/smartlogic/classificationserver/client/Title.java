package com.smartlogic.classificationserver.client;

/**
 * Container for the title data of a document to classify
 * @author Smartlogic Semaphore
 *
 */
public class Title extends StringObject {
	protected String getParameterName() { return "title"; }
	
	private final String value;
	/**
	 * Create the title from the supplied data
	 * @param value The title
	 */
	public Title(String value) {
		this.value = value;
	}
	protected String getValue() {
		return value;
	}
}
