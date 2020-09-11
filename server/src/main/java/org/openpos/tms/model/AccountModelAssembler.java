package org.openpos.tms.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.openpos.tms.controller.AccountController;
import org.openpos.tms.controller.TerminalController;
import org.openpos.tms.dao.dataobject.Account;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AccountModelAssembler
		extends RepresentationModelAssemblerSupport<Account, AccountModel> {

	public AccountModelAssembler() {
		super(AccountController.class, AccountModel.class);
	}

	@Override
	public AccountModel toModel(Account entity) {
		AccountModel accountModel = instantiateModel(entity);
		
		accountModel.add(linkTo(methodOn(AccountController.class).getAccountById(entity.getId())).withSelfRel());
		accountModel.add(linkTo(methodOn(TerminalController.class).getTerminals(entity.getId(), null)).withRel("terminals"));
		
		accountModel.setId(entity.getId());
		accountModel.setContractNumber(entity.getContractNumber());
		accountModel.setDisplayName(entity.getDisplayName());
		return accountModel;
	}
}
