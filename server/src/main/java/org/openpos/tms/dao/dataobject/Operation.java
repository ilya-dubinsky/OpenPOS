package org.openpos.tms.dao.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Operation extends BaseDataObject {
	@Id
	@GeneratedValue
	private long id;
	private String type;
	private String description;

//	@ManyToMany
//	@JoinTable(name = "ops_terminals", joinColumns = @JoinColumn(name = "op_id"),
//			inverseJoinColumns = @JoinColumn(name = "terminal_id"))
//	private Collection<Terminal> terminals = new ArrayList<>();

	public Operation(String type, String description) {
		this.type = type;
		this.description = description;
	}

//	public void addTerminal(Terminal terminal) {
//		if (null == terminal)
//			return;
//		if (terminals.contains(terminal))
//			return;
//		terminals.add(terminal);
//		terminal.addOperation(this);
//	}

}