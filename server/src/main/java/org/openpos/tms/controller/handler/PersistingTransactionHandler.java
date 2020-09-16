package org.openpos.tms.controller.handler;

import org.openpos.tms.dao.CurrencyRepository;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.TransactionRepository;
import org.openpos.tms.dao.dataobject.Currency;
import org.openpos.tms.dao.dataobject.Terminal;
import org.openpos.tms.dao.dataobject.Transaction;
import org.openpos.tms.errors.CurrencyNotFoundException;
import org.openpos.tms.errors.TerminalNotFoundException;
import org.openpos.tms.protocol.message.Message;
import org.openpos.tms.protocol.message.POSMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class PersistingTransactionHandler extends POSTransactionHandler {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TerminalRepository terminalRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	protected TransactionHandlerResult handleTransaction(Message transaction) {
		POSMessage posMessage = (POSMessage) transaction;

		Transaction transactionDO = new Transaction();
		BeanUtils.copyProperties(posMessage, transactionDO, "currency");

		Terminal terminal = terminalRepository.findById(posMessage.getTerminalId())
				.orElseThrow(() -> new TerminalNotFoundException(posMessage.getTerminalId()));
		Currency currency = currencyRepository.findById(posMessage.getCurrency())
				.orElseThrow(() -> new CurrencyNotFoundException(posMessage.getCurrency()));
		
		transactionDO.setType(posMessage.getOperation());
		transactionDO.setCurrency(currency);
		transactionDO.setTerminal(terminal);
		transactionDO = transactionRepository.save(transactionDO);
		long transactionId = transactionDO.getId();
		posMessage.setTransactionId(transactionId);
		
		// TODO: save message
		return next(posMessage);
	}

}
