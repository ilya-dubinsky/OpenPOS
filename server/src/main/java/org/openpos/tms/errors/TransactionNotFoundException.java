package org.openpos.tms.errors;

public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(long transactionId) {
		super(String.format("Can't find transaction %d", transactionId));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5308734310491467034L;

}
