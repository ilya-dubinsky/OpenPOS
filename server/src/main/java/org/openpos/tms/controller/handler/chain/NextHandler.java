package org.openpos.tms.controller.handler.chain;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.openpos.tms.controller.handler.AbstractTransactionHandler;

@Retention(RUNTIME)
@Target(METHOD)
public @interface NextHandler {
	Class<? extends AbstractTransactionHandler> value ();
}
