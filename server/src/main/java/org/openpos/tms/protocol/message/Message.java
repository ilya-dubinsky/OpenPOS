package org.openpos.tms.protocol.message;

import lombok.Data;

@Data
public abstract class Message {
	private String resultCode;
	private String resultDescription;
}
