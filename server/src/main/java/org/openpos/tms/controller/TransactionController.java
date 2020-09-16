package org.openpos.tms.controller;

import org.openpos.tms.controller.handler.SourceHandler;
import org.openpos.tms.controller.handler.TransactionHandlerResult;
import org.openpos.tms.controller.handler.TransactionHandlerResult.ChainResult;
import org.openpos.tms.dao.TransactionRepository;
import org.openpos.tms.dao.dataobject.Transaction;
import org.openpos.tms.errors.TransactionNotFoundException;
import org.openpos.tms.errors.TransactionProcessingException;
import org.openpos.tms.model.POSMessageModel;
import org.openpos.tms.model.POSMessageModelAssembler;
import org.openpos.tms.model.TransactionModel;
import org.openpos.tms.model.TransactionModelAssembler;
import org.openpos.tms.protocol.message.POSMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
	@Autowired
	private SourceHandler sourceHandler;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private POSMessageModelAssembler posMessageModelAssembler;
	
	@Autowired
	private TransactionModelAssembler transactionModelAssembler;

	@PostMapping("/api/transactions")
	public ResponseEntity<POSMessageModel> processTransaction(@RequestBody POSMessage posMessage) {
		TransactionHandlerResult result = sourceHandler.handleTransactionChain(posMessage)
				.orElseThrow(() -> new TransactionProcessingException("Error handling transaction"));
		POSMessageModel model = posMessageModelAssembler.toModel(result.getOutput());
		if (result.getResult() == ChainResult.ERROR)
			return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@GetMapping("/api/transactions/{transactionId}")
	public ResponseEntity<TransactionModel> getTransaction(@PathVariable long transactionId) {
		Transaction tranDO = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionNotFoundException(transactionId));
		return new ResponseEntity<>(transactionModelAssembler.toModel(tranDO), HttpStatus.OK);
	}

}
