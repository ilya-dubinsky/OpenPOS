package org.openpos.tms.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value="account")
@Relation(collectionRelation = "accounts", itemRelation="account")
@JsonInclude(Include.NON_NULL)
public class AccountModel extends RepresentationModel<AccountModel> {

	private Long id;
	private String contractNumber;
	private String displayName;
	
	private List<TerminalModel> terminals;
}
