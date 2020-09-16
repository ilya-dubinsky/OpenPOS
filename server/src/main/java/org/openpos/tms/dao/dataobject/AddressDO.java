package org.openpos.tms.dao.dataobject;

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
public class AddressDO extends BaseDO {

	@OneToOne( optional = false)
	@JoinColumn(name = "country_code", referencedColumnName = "alpha2")
	private CountryDO country;

	private String city;
	private String zip;
	private String line1;
	private String line2;

	@Id
	@GeneratedValue
	private long id;

	

}
