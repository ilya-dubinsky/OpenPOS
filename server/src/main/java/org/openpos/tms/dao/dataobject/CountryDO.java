package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor 
public class CountryDO extends BaseDO {

	@Id
	private String alpha2;
	private String alpha3;
	private String numeric;
	
	public CountryDO(String alpha2, String alpha3, String numeric) {
		this.alpha2 = alpha2;
		this.alpha3 = alpha3;
		this.numeric = numeric;
	}
	
}
