package org.openpos.tms.errors;

public class CurrencyNotFoundException extends RuntimeException {

	public CurrencyNotFoundException(String currency) {
		super(String.format("Can't find currency %s", currency));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007708375772774360L;

}
