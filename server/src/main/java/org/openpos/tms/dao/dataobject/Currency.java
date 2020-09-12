package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Currency extends BaseDataObject {
	@Id
	private String alpha3;
	private String numeric;
	private String name;
}
