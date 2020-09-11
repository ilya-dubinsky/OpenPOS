package org.openpos.tms.protocol.impl;

import org.openpos.tms.protocol.TransactionProtocol;
import org.springframework.stereotype.Service;

@Service
public class InternalProtocol implements TransactionProtocol {

	@Override
	public String getHandler() {
		return "Internal";
	}

}
