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
public class Terminal extends BaseDataObject {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeFrom = new Date(); // TODO retrieve from a service?

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeTo = new Date(Long.MAX_VALUE);

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "ops_terminals", joinColumns = @JoinColumn(name = "terminal_id"),
			inverseJoinColumns = @JoinColumn(name = "op_id"))
	@Setter(AccessLevel.NONE)
	private Set<Operation> operations = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name= "active_key_id")
	private PublicKey publicKey;
	
	@ManyToOne
	private Account account;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private @Id @GeneratedValue long id;

	public long getTerminalId() {
		return id;
	}

	public void setTerminalId(long uuid) {
		this.id = uuid;
	}

	public void addOperation(Operation newOperation) {
		if (null == newOperation)
			return;
		if (this.operations.contains(newOperation))
			return;
		operations.add(newOperation);
	}

	public void removeOperation(Operation op) {
		if (null == op)
			return;
		if (!this.operations.contains(op))
			return;
		operations.remove(op);
	}

}
