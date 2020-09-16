package org.openpos.tms.dao.dataobject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "terminal")
@Data
@EqualsAndHashCode(callSuper = false)
public class TerminalDO extends BaseDO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeFrom = new Date(); // TODO retrieve from a service?

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeTo = new Date(Long.MAX_VALUE);

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "address_id")
	private AddressDO address;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "ops_terminals", joinColumns = @JoinColumn(name = "terminal_id"),
			inverseJoinColumns = @JoinColumn(name = "op_id"))
	@Setter(AccessLevel.NONE)
	private Set<OperationDO> operations = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name= "active_key_id")
	private PublicKeyDO publicKey;
	
	@ManyToOne
	private AccountDO account;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private @Id @GeneratedValue long id;

	public long getTerminalId() {
		return id;
	}

	public void setTerminalId(long uuid) {
		this.id = uuid;
	}

	public void addOperation(OperationDO newOperation) {
		if (null == newOperation)
			return;
		if (this.operations.contains(newOperation))
			return;
		operations.add(newOperation);
	}

	public void removeOperation(OperationDO op) {
		if (null == op)
			return;
		if (!this.operations.contains(op))
			return;
		operations.remove(op);
	}

}
