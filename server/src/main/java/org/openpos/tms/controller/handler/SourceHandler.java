package org.openpos.tms.controller.handler;

import org.openpos.tms.protocol.message.Message;

public class SourceHandler extends AbstractTransactionHandler {

	@Override
	protected TransactionHandlerResult handleTransaction(Message transaction) {
		return next(transaction);
	}

	@Override
	protected boolean canHandle(Class<? extends Message> class1) {
		return true;
	}

}
