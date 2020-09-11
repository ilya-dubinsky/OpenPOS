package org.openpos.tms.protocol;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProtocolFactory {
	private final Map<String, TransactionProtocol> protoMap;

	@Autowired
	public ProtocolFactory(List<TransactionProtocol> protocols) {
		protoMap = protocols.stream().collect(Collectors.toMap(TransactionProtocol::getHandler, Function.identity()));
	}
	
	public TransactionProtocol getProtocol(String handler) {
		return protoMap.get(handler);
	}
}
