package org.openpos.tms.controller.handler;

import org.openpos.tms.protocol.message.Message;
import org.springframework.stereotype.Component;

@Component
public class SinkHandler extends AbstractTransactionHandler {

	@Override
	protected TransactionHandlerResult handleTransaction(Message transaction) {
		return done(transaction);
	}

	@Override
	protected boolean canHandle(Class<? extends Message> class1) {
		return true;
	}

}
