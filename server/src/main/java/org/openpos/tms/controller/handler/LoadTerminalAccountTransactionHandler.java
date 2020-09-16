package org.openpos.tms.controller.handler;

import java.util.Optional;

import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.Terminal;
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
		Optional<Terminal> terminal = terminalRepository.findById(terminalId);
		if (!terminal.isPresent())
			return error(ErrorMessage.validationError("Failed to find terminal %d", terminalId));
		long accountId = terminal.get().getAccount().getId();
		transaction.setAccountId(accountId);
		
		return next(transaction);
	}

}
