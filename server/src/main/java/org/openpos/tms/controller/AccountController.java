package org.openpos.tms.controller;

import org.openpos.tms.dao.AccountRepository;
import org.openpos.tms.dao.dataobject.AccountDO;
import org.openpos.tms.model.AccountModel;
import org.openpos.tms.model.AccountModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController extends BaseController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountModelAssembler accountModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AccountDO> pagedResourcesAssembler;
	
	@GetMapping("/api/accounts/{id}")
	public ResponseEntity<AccountModel> getAccountById(@PathVariable Long id) {
		return accountRepository.findById(id)
				.map(accountModelAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/api/accounts")
	public ResponseEntity<PagedModel<AccountModel>> getAllAccounts(Pageable pageable) {
		Page<AccountDO> accountEntities = accountRepository.findAll(pageable);
		PagedModel<AccountModel> collModel = pagedResourcesAssembler.toModel(accountEntities, accountModelAssembler);
		return new ResponseEntity<>(collModel, HttpStatus.OK);
	}
	
	@PostMapping("/api/accounts")
	public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel accountModel ) {
		AccountDO accountDO = new AccountDO(accountModel.getDisplayName(), accountModel.getContractNumber());
		accountDO = accountRepository.save(accountDO);
		return new ResponseEntity<>(accountModelAssembler.toModel(accountDO),HttpStatus.OK);
	}
	
}
