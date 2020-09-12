package org.openpos.tms.protocol.message;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Message {

	private long terminalId;
	private String operation;

	private String pan;
	private BigDecimal amount;
	private String currency;
}
