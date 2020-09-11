package org.openpos.tms.dao.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Acquirer extends BaseDataObject {
	@Id @GeneratedValue
	private long id;
	
	@Column(length = 128)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "protocol_id")
	private Protocol protocol;
}
