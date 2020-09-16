package org.openpos.tms.model;

import java.math.BigDecimal;

import org.openpos.tms.infra.Utils;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class POSMessageModel extends RepresentationModel<POSMessageModel>{
	private Long id;
	private String operation;
	
	private long transactionId;

	private String pan;
	private BigDecimal amount;
	private String currency;

	private String resultCode;
	private String resultDescription;

	public String getPan() {
		return Utils.maskPan(pan);
	}
}
