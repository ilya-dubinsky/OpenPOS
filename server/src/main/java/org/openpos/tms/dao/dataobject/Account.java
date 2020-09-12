package org.openpos.tms.dao.dataobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Account extends BaseDataObject {

	@Id
	@GeneratedValue
	private long id;
	@Column(length = 128)
	@NonNull
	private String displayName;

	@Column(length = 128)
	@NonNull
	private String contractNumber;

	@Setter(AccessLevel.NONE)
	@OneToMany
	@JoinColumn(name = "account_id")
	private final Set<Terminal> terminals = new HashSet<>();

	@Setter(AccessLevel.NONE)
	@ManyToMany
	@JoinTable(name = "accounts_acquirers", joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "acquirer_id"))
	private final Set<Acquirer> acquirers = new HashSet<>();

}
