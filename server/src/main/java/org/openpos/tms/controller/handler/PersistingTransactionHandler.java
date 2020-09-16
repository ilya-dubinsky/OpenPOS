package org.openpos.tms.controller.handler;

import org.openpos.tms.dao.CurrencyRepository;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.TransactionRepository;
import org.openpos.tms.dao.dataobject.CurrencyDO;
import org.openpos.tms.dao.dataobject.TerminalDO;
import org.openpos.tms.dao.dataobject.TransactionDO;
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

		TransactionDO transactionDO = new TransactionDO();
		BeanUtils.copyProperties(posMessage, transactionDO, "currency");

		TerminalDO terminalDO = terminalRepository.findById(posMessage.getTerminalId())
				.orElseThrow(() -> new TerminalNotFoundException(posMessage.getTerminalId()));
		CurrencyDO currencyDO = currencyRepository.findById(posMessage.getCurrency())
				.orElseThrow(() -> new CurrencyNotFoundException(posMessage.getCurrency()));
		
		transactionDO.setType(posMessage.getOperation());
		transactionDO.setCurrency(currencyDO);
		transactionDO.setTerminal(terminalDO);
		transactionDO = transactionRepository.save(transactionDO);
		
		long transactionId = transactionDO.getId();
		posMessage.setTransactionId(transactionId);
		
		// TODO: save message
		return next(posMessage);
	}

}
