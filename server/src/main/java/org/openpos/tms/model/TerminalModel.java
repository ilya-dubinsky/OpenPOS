package org.openpos.tms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TerminalModel extends RepresentationModel<TerminalModel> {
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date activeTo;
	
	private String country;

	private String city;
	private String zip;
	private String line1;
	private String line2;
	
	private final List<String> operations = new ArrayList<>();
}
