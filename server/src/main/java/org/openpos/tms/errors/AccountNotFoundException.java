package org.openpos.tms.errors;

public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1028181978273299923L;

	public AccountNotFoundException(long id) {
		super(String.format("Couldn't find account by ID: %d",id));
	}
}
