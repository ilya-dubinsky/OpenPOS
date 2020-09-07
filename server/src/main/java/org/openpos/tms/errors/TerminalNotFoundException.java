package org.openpos.tms.errors;

import java.util.UUID;

public class TerminalNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8219911176968442343L;

	public TerminalNotFoundException( String id) {
		super(String.format("Could not find terminal %s", id));
	}
	
	public TerminalNotFoundException(UUID id) {
		super(String.format("Could not find terminal %s", String.valueOf(id)));

	}
}
