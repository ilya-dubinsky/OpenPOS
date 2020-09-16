package org.openpos.tms.model;

import java.math.BigDecimal;

import org.openpos.tms.infra.Utils;
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
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@EqualsAndHashCode(callSuper = false) 
@JsonRootName(value="terminal")
@Relation(collectionRelation = "terminals", itemRelation = "terminal")
@JsonInclude(Include.NON_NULL)
@ToString
public class TransactionModel extends RepresentationModel<TransactionModel>{
	private long id;
	private String pan;
	private BigDecimal amount;
	private String currency;
	private String resultCode;
	
	public String getPan()
	{
		return Utils.maskPan(pan);
	}

}
