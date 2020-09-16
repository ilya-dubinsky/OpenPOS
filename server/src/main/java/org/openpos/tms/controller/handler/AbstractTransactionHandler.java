package org.openpos.tms.controller.handler;

import java.util.Optional;

import org.openpos.tms.controller.handler.TransactionHandlerResult.ChainResult;
import org.openpos.tms.protocol.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractTransactionHandler {

	@Autowired
	private ApplicationContext applicationContext; 
	
	private AbstractTransactionHandler next;

	private Class<? extends AbstractTransactionHandler> nextClass = SinkHandler.class;
	
	protected Class< ? extends Message> messageClass = null;

	protected abstract TransactionHandlerResult handleTransaction(Message transaction);

	public Optional<TransactionHandlerResult> handleTransactionChain(Message transaction) {
		if (!canHandle(transaction.getClass()))
			return Optional.ofNullable(null);

		TransactionHandlerResult result = this.handleTransaction(transaction);
		
		switch (result.getResult()) {
		case NEXT:
			return getNext().handleTransactionChain(result.getOutput());
		case ERROR:
		case DONE:
		default:
			return Optional.of(result);
		}
	}

	private AbstractTransactionHandler getNext() {
		if (next == null)
			next = (AbstractTransactionHandler) applicationContext.getBean(nextClass);
		return next;
	}

	protected boolean canHandle(Class<? extends Message> class1) {
		return false;
	}
	
	protected TransactionHandlerResult done(Message transaction) {
		return new TransactionHandlerResult(transaction, ChainResult.DONE);
	}

	protected TransactionHandlerResult next(Message transaction) {
		return new TransactionHandlerResult(transaction, ChainResult.NEXT);
	}
	
	protected TransactionHandlerResult error(Message transaction) {
		return new TransactionHandlerResult(transaction, ChainResult.ERROR);
	}

	/**
	 * @return the nextClass
	 */
	public Class<? extends AbstractTransactionHandler> getNextClass() {
		return nextClass;
	}

	/**
	 * @param nextClass the nextClass to set
	 */
	public void setNextClass(Class<? extends AbstractTransactionHandler> nextClass) {
		this.nextClass = nextClass;
	}
	
}
