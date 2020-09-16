package org.openpos.tms.controller.handler;

import java.util.Optional;

import org.openpos.tms.dao.OperationRepository;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.Operation;
import org.openpos.tms.dao.dataobject.Terminal;
import org.openpos.tms.errors.TerminalNotFoundException;
import org.openpos.tms.protocol.message.ErrorMessage;
import org.openpos.tms.protocol.message.Message;
import org.openpos.tms.protocol.message.POSMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateOperationTransactionHandler extends POSTransactionHandler {
	@Autowired
	private TerminalRepository terminalRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	protected TransactionHandlerResult handleTransaction(Message transaction) {
		POSMessage message = (POSMessage) transaction;
		String op = message.getOperation();
		Optional<Operation> opSearch = operationRepository.findByType(op);
		if (!opSearch.isPresent())
			return error(ErrorMessage.validationError("Unknown operation %s", op));

		long terminalId = message.getTerminalId();
		Terminal terminal = terminalRepository.findById(terminalId)
				// this is handled by a separate handler and shouldn't happen here
				.orElseThrow(() -> new TerminalNotFoundException(terminalId));
		
		if (!terminal.getOperations().contains(opSearch.get()))
			return error(ErrorMessage.validationError("Operation %s not allowed for terminal %d", op, terminalId));
		else
			return next(transaction);
			
	}

}
