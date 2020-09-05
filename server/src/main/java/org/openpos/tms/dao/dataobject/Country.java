package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country extends BaseDataObject {

	@Id
	private String alpha2;

	private String alpha3;
	private String numeric;
	
	/**
	 * @return the alpha2
	 */
	public String getAlpha2() {
		return alpha2;
	}

	/**
	 * @param alpha2 the alpha2 to set
	 */
	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	/**
	 * @return the alpha3
	 */
	public String getAlpha3() {
		return alpha3;
	}

	/**
	 * @param alpha3 the alpha3 to set
	 */
	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

	/**
	 * @return the numeric
	 */
	public String getNumeric() {
		return numeric;
	}

	/**
	 * @param numeric the numeric to set
	 */
	public void setNumeric(String numeric) {
		this.numeric = numeric;
	}
	
	public Country() {
		
	}

	public Country(String alpha2, String alpha3, String numeric) {
		this.alpha2 = alpha2;
		this.alpha3 = alpha3;
		this.numeric = numeric;
	}
}
