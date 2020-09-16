package org.openpos.tms.protocol.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorMessage extends Message {
	public static final String VALIDATION_ERROR_RC="E096";
	
	public static ErrorMessage validationError(String format, Object ... args) {
		return validationError(String.format(format, args));
	}
	
	public static ErrorMessage validationError(String description) {
		ErrorMessage errMesg = new ErrorMessage();
		errMesg.setResultCode(VALIDATION_ERROR_RC);
		errMesg.setResultDescription(description);
		return errMesg;
	}
}
