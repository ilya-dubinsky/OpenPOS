package org.openpos.tms.dao.dataobject;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author idubinsky
 * 
 *         For each terminal, there is a public key associated with it. Any
 *         message from the terminal is to be signed with terminal's embedded
 *         private key. If the signature doesn't match, the terminal's
 *         "firmware" is not in sync with the TMS server.
 * 
 *         This is different from DUKPT scheme which is used for the PIN blocks
 *         only.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString
public class PublicKey extends BaseDataObject {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(columnDefinition = "VARCHAR(64)")
	private String fingerprint;
	
	@Lob
	private byte[] key;
	
	@Column(name="expiry_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expiryDate = java.sql.Date.valueOf(LocalDate.now().plusDays(5*365));
	
	@JsonIgnore
	public byte[] getKey() {
		return key;
	}
	
	@JsonDeserialize()
	public void setKey(byte[] key) {
		this.key = key;
	}
}
