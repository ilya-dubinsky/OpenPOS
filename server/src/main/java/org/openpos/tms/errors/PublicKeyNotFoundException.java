package org.openpos.tms.errors;

public class PublicKeyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5138423611708888300L;

	public PublicKeyNotFoundException(long id) {
		super(String.format("Could not find public key by id %d", id));
	}
}
