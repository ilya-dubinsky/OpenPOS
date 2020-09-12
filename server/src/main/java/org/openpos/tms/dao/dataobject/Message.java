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
public class Message extends BaseDataObject {
	private @Id @GeneratedValue long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "terminal_id")
	private Terminal terminal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "protocol_id")
	private Protocol protocol;
	
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;
	
	@Lob
	private byte[] data;
}
