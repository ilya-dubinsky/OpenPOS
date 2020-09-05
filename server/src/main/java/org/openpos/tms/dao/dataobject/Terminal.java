package org.openpos.tms.dao.dataobject;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Terminal extends BaseDataObject {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeFrom = new Date(); // TODO retrieve from a service?

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeTo = new Date(Long.MAX_VALUE);

	@ManyToOne(cascade = CascadeType.MERGE)
	private Address address;

	private @Id @GeneratedValue UUID terminalId;

	/**
	 * @return the activeFrom
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getActiveFrom() {
		return activeFrom;
	}

	/**
	 * @return the activeTo
	 */
	public Date getActiveTo() {
		return activeTo;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return the terminalId
	 */
	public UUID getTerminalId() {
		return terminalId;
	}


	/**
	 * @param activeFrom the activeFrom to set
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}

	/**
	 * @param activeTo the activeTo to set
	 */
	public void setActiveTo(Date activeTo) {
		this.activeTo = activeTo;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(UUID terminalId) {
		this.terminalId = terminalId;
	}

}
