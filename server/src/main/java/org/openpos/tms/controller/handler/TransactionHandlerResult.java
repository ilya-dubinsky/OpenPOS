package org.openpos.tms.controller.handler;

import org.openpos.tms.protocol.message.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TransactionHandlerResult {
	public enum ChainResult {
		NEXT, DONE, ERROR
	}
	
	private Message output;
	private ChainResult result;
}
