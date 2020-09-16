package org.openpos.tms.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.openpos.tms.controller.TerminalController;
import org.openpos.tms.dao.dataobject.Account;
import org.openpos.tms.dao.dataobject.Address;
import org.openpos.tms.dao.dataobject.Country;
import org.openpos.tms.dao.dataobject.Operation;
import org.openpos.tms.dao.dataobject.Terminal;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TerminalModelAssembler extends RepresentationModelAssemblerSupport<Terminal, TerminalModel> {

	public TerminalModelAssembler() {
		super(TerminalController.class, TerminalModel.class);
	}

	@Override
	public TerminalModel toModel(Terminal entity) {
		TerminalModel terminalModel = instantiateModel(entity);
		Account account = entity.getAccount();

		terminalModel
				.add(linkTo(methodOn(TerminalController.class).getTerminal(account.getId(), entity.getTerminalId()))
						.withSelfRel());
		terminalModel.add(
				linkTo(methodOn(TerminalController.class).getTerminals(account.getId(), null)).withRel("collection"));

		BeanUtils.copyProperties(entity, terminalModel);
		terminalModel.setId(entity.getTerminalId());
//		terminalModel.setActiveFrom(entity.getActiveFrom());
//		terminalModel.setActiveTo(entity.getActiveTo());

		Address address = entity.getAddress();
		if (address != null) {
			BeanUtils.copyProperties(address, terminalModel, "country");
//			terminalModel.setCity(address.getCity());
//			terminalModel.setLine1(address.getLine1());
//			terminalModel.setLine2(address.getLine2());
			Country country = address.getCountry();
			if (country != null)
				terminalModel.setCountry(country.getAlpha2());
		}

		for (Operation op : entity.getOperations()) {
			terminalModel.getOperations().add(op.getType());
		}
		return terminalModel;
	}

}
