package org.openpos.tms.dao.dataobject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TransactionDO extends BaseDO {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "terminal_id")
	private TerminalDO terminal;
	
	private BigDecimal amount;
	
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="currency", referencedColumnName = "alpha3")
	private CurrencyDO currency;
	
	@OneToMany
	@JoinColumn(name = "transaction_id")
	private List<MessageDO> messages = new ArrayList<>();
	
}
