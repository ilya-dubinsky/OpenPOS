package org.openpos.tms.protocol.message;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class POSMessage extends Message{

	private long terminalId;
	private String operation;
	
	private long accountId;
	private long transactionId;
	private long messageId;

	private String pan;
	private BigDecimal amount;
	private String currency;
}
