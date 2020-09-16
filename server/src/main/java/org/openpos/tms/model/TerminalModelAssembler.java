package org.openpos.tms.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.openpos.tms.controller.TerminalController;
import org.openpos.tms.dao.dataobject.AccountDO;
import org.openpos.tms.dao.dataobject.AddressDO;
import org.openpos.tms.dao.dataobject.CountryDO;
import org.openpos.tms.dao.dataobject.OperationDO;
import org.openpos.tms.dao.dataobject.TerminalDO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TerminalModelAssembler extends RepresentationModelAssemblerSupport<TerminalDO, TerminalModel> {

	public TerminalModelAssembler() {
		super(TerminalController.class, TerminalModel.class);
	}

	@Override
	public TerminalModel toModel(TerminalDO entity) {
		TerminalModel terminalModel = instantiateModel(entity);
		AccountDO accountDO = entity.getAccount();

		terminalModel
				.add(linkTo(methodOn(TerminalController.class).getTerminal(accountDO.getId(), entity.getTerminalId()))
						.withSelfRel());
		terminalModel.add(
				linkTo(methodOn(TerminalController.class).getTerminals(accountDO.getId(), null)).withRel("collection"));

		BeanUtils.copyProperties(entity, terminalModel);
		terminalModel.setId(entity.getTerminalId());
//		terminalModel.setActiveFrom(entity.getActiveFrom());
//		terminalModel.setActiveTo(entity.getActiveTo());

		AddressDO addressDO = entity.getAddress();
		if (addressDO != null) {
			BeanUtils.copyProperties(addressDO, terminalModel, "country");
//			terminalModel.setCity(address.getCity());
//			terminalModel.setLine1(address.getLine1());
//			terminalModel.setLine2(address.getLine2());
			CountryDO countryDO = addressDO.getCountry();
			if (countryDO != null)
				terminalModel.setCountry(countryDO.getAlpha2());
		}

		for (OperationDO op : entity.getOperations()) {
			terminalModel.getOperations().add(op.getType());
		}
		return terminalModel;
	}

}
