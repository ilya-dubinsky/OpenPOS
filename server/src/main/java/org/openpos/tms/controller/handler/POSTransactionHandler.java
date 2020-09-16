package org.openpos.tms.controller.handler;

import org.openpos.tms.protocol.message.Message;
import org.openpos.tms.protocol.message.POSMessage;

public abstract class POSTransactionHandler extends AbstractTransactionHandler {

	@Override
	protected boolean canHandle(Class<? extends Message> class1) {
		return (POSMessage.class.isAssignableFrom(class1));
	}

}
