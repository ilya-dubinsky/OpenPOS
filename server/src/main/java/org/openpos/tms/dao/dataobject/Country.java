package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="country")
@Getter @Setter 
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @ToString
public class Country extends BaseDataObject {

	@Id
	private String alpha2;
	private String alpha3;
	private String numeric;
	
	public Country(String alpha2, String alpha3, String numeric) {
		this.alpha2 = alpha2;
		this.alpha3 = alpha3;
		this.numeric = numeric;
	}
	
}
