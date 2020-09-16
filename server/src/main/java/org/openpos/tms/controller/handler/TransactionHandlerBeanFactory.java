package org.openpos.tms.controller.handler;

import java.lang.reflect.Method;

import org.openpos.tms.controller.handler.chain.NextHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TransactionHandlerBeanFactory  {
	// TODO maybe redo it as a bean factory

	@Bean
	@NextHandler(LoadTerminalAccountTransactionHandler.class)
	public SourceHandler getSourceHandler() {
		return getHandler(new Object(){}.getClass().getEnclosingMethod(), SourceHandler.class);
	}
	
	@Bean 
	@NextHandler(ValidateOperationTransactionHandler.class)
	public LoadTerminalAccountTransactionHandler getValidateTerminalTransactionHandler() {
		return getHandler(new Object(){}.getClass().getEnclosingMethod(), LoadTerminalAccountTransactionHandler.class);
	}
	
	@Bean
	@NextHandler(PersistingTransactionHandler.class)
	public ValidateOperationTransactionHandler getValidateOperationTransactionHandler() {
		return getHandler(new Object() {}.getClass().getEnclosingMethod(), ValidateOperationTransactionHandler.class);
	}
	
	@Bean
	@NextHandler(SinkHandler.class)
	public PersistingTransactionHandler getPersistingTransactionHandler() {
		return getHandler(new Object() {}.getClass().getEnclosingMethod(), PersistingTransactionHandler.class);
	}
	
	private <T extends AbstractTransactionHandler>T getHandler(Method caller, Class<T> clazz) {
		T handler;
		try {
			handler = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error("Failed to instantiate transaction handler {}", clazz.getName());
			return null;
		}
		
		NextHandler nextHandler[] = caller.getAnnotationsByType(NextHandler.class);
		if (nextHandler != null && nextHandler.length>0) {
			handler.setNextClass(nextHandler[0].value());
		}
		return handler;
	}
	
	public TransactionHandlerBeanFactory() {
		
	}

}
