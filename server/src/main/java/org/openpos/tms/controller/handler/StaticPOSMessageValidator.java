package org.openpos.tms.controller.handler;

import org.openpos.tms.protocol.message.POSMessage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StaticPOSMessageValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return POSMessage.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO add validations per POS message type
		// TODO turn this into a Handler?!
		POSMessage message = (POSMessage)target;
		
		if (message.getAmount() == null)
			errors.rejectValue("amount", "Amount empty");
		
		if (message.getCurrency() == null)
			errors.rejectValue("currency", "Currency not provided");
	}

}
