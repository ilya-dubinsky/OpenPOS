package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="address")
@Getter @Setter
@EqualsAndHashCode(callSuper = false) @ToString
public class Address extends BaseDataObject {

	@OneToOne( optional = false)
	@JoinColumn(name = "country_code", referencedColumnName = "alpha2")
	private Country country;

	private String city;
	private String zip;
	private String line1;
	private String line2;

	@Id
	@GeneratedValue
	private long id;

	

}
