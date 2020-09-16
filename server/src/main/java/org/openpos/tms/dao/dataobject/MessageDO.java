package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class MessageDO extends BaseDO {
	private @Id @GeneratedValue long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "terminal_id")
	private TerminalDO terminal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "protocol_id")
	private ProtocolDO protocol;
	
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private TransactionDO transaction;
	
	@Lob
	private byte[] data;
}
