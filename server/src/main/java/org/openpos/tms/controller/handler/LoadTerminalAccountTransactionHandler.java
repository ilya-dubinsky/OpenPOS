package org.openpos.tms.controller.handler;

import java.util.Optional;

import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.TerminalDO;
import org.openpos.tms.protocol.message.ErrorMessage;
import org.openpos.tms.protocol.message.Message;
import org.openpos.tms.protocol.message.POSMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class LoadTerminalAccountTransactionHandler extends POSTransactionHandler {


	@Autowired
	private TerminalRepository terminalRepository;

	@Override
	protected TransactionHandlerResult handleTransaction(Message message) {
		POSMessage transaction = (POSMessage) message;
		long terminalId = transaction.getTerminalId();
		Optional<TerminalDO> terminalDO = terminalRepository.findById(terminalId);
		if (!terminalDO.isPresent())
			return error(ErrorMessage.validationError("Failed to find terminal %d", terminalId));
		long accountId = terminalDO.get().getAccount().getId();
		transaction.setAccountId(accountId);
		
		return next(transaction);
	}

}
