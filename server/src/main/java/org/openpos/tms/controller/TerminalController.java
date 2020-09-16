package org.openpos.tms.controller;

import org.openpos.tms.dao.AccountRepository;
import org.openpos.tms.dao.AddressRepository;
import org.openpos.tms.dao.CountryRepository;
import org.openpos.tms.dao.OperationRepository;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.Account;
import org.openpos.tms.dao.dataobject.Address;
import org.openpos.tms.dao.dataobject.Country;
import org.openpos.tms.dao.dataobject.Operation;
import org.openpos.tms.dao.dataobject.Terminal;
import org.openpos.tms.errors.AccountNotFoundException;
import org.openpos.tms.errors.CountryNotFoundException;
import org.openpos.tms.errors.OperationNotFoundException;
import org.openpos.tms.errors.TerminalNotFoundException;
import org.openpos.tms.infra.PublicServiceMethod;
import org.openpos.tms.model.TerminalModel;
import org.openpos.tms.model.TerminalModelAssembler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TerminalController extends BaseController {

	@Autowired
	private TerminalRepository terminalRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private TerminalModelAssembler terminalModelAssembler;

	@Autowired
	private PagedResourcesAssembler<Terminal> pagedTerminalResourcesAssembler;

	@PostMapping("/api/accounts/{accountId}/terminals")
	@PublicServiceMethod
	public ResponseEntity<TerminalModel> createTerminalAction(@PathVariable long accountId,
			@RequestBody(required = true) TerminalModel terminalModel) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException(accountId));

		Terminal terminal = new Terminal();
		BeanUtils.copyProperties(terminalModel, terminal);
		terminal.setAccount(account);

		Country country = countryRepository.findById(terminalModel.getCountry()).orElseThrow(() -> new CountryNotFoundException(terminalModel.getCountry()));
		Address address = new Address();
		
		address.setCountry(country);
		BeanUtils.copyProperties(terminalModel, address);
		terminal.setAddress(address);
		address = addressRepository.save(address);

		Terminal storedTerminal = terminalRepository.save(terminal);
		return new ResponseEntity<>(terminalModelAssembler.toModel(storedTerminal), HttpStatus.OK);
	}

	@GetMapping("/api/accounts/{accountId}/terminals")
	@PublicServiceMethod
	public ResponseEntity<PagedModel<TerminalModel>> getTerminals(@PathVariable long accountId, Pageable pageable) {

		// discarding the result of the search
		accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));

		Page<Terminal> terminals = terminalRepository.findByAccountId(pageable, accountId);
		PagedModel<TerminalModel> coll = pagedTerminalResourcesAssembler.toModel(terminals, terminalModelAssembler);
		return new ResponseEntity<>(coll, HttpStatus.OK);
	}

	@GetMapping("/api/accounts/{accountId}/terminals/{terminalId}")
	@PublicServiceMethod
	public ResponseEntity<TerminalModel> getTerminal(@PathVariable long accountId, @PathVariable long terminalId) {
		accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));

		Terminal terminal = terminalRepository.findById(terminalId).orElseThrow(() -> new TerminalNotFoundException(terminalId));
		return new ResponseEntity<>(terminalModelAssembler.toModel(terminal), HttpStatus.OK);
	}

	@PostMapping("/api/accounts/{accountId}/terminals/{terminalId}/operations/{type}")
	@PublicServiceMethod
	public ResponseEntity<TerminalModel> addTerminalOperationAction(@PathVariable long accountId, @PathVariable long terminalId, @PathVariable String type) {
		accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
		Terminal terminal = terminalRepository.findById(terminalId).orElseThrow(() -> new TerminalNotFoundException(terminalId));
		Operation op = operationRepository.findByType(type).orElseThrow(() -> new OperationNotFoundException(type));
		terminal.addOperation(op);
		terminalRepository.save(terminal);
		LOGGER.info("Adding operation {} to terminal {}", op.getType(), terminal.getTerminalId());
		return new ResponseEntity<>(terminalModelAssembler.toModel(terminal), HttpStatus.OK);
	}

	@DeleteMapping("/api/accounts/{accountId}/terminals/{terminalId}/operations/{type}")
	@PublicServiceMethod
	public void delTerminalOperationAction(@PathVariable long accountId, @PathVariable long terminalId, @PathVariable String type) {
		accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
		Terminal terminal = terminalRepository.findById(terminalId).orElseThrow(() -> new TerminalNotFoundException(terminalId));
		Operation op = operationRepository.findByType(type).orElseThrow(() -> new OperationNotFoundException(type));
		terminal.removeOperation(op);
		terminalRepository.save(terminal);
		LOGGER.info("Removing operation {} from terminal {}", op.getType(), terminal.getTerminalId());
	}

}
