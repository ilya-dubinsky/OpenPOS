package org.openpos.tms.errors;

public class TerminalNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8219911176968442343L;

	public TerminalNotFoundException( String id) {
		super(String.format("Could not find terminal %s", id));
	}
	
	public TerminalNotFoundException(long id) {
		super(String.format("Could not find terminal %d", id));

	}
}
