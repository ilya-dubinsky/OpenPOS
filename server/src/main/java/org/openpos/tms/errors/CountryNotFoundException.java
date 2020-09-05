package org.openpos.tms.errors;

public class CountryNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 214072689307513305L;

	public CountryNotFoundException( String id) {
		super(String.format("Could not find country %s", id));
	}

}
