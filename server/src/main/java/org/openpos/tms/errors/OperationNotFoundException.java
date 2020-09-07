package org.openpos.tms.errors;

public class OperationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1673744121389538700L;

	public OperationNotFoundException(String type) {
		super(String.format("Can't find operation type %s", type));
	}

}
