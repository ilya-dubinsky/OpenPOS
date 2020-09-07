package org.openpos.tms.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.openpos.tms.dao.AddressRepository;
import org.openpos.tms.dao.OperationRepository;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.Operation;
import org.openpos.tms.dao.dataobject.Terminal;
import org.openpos.tms.errors.OperationNotFoundException;
import org.openpos.tms.errors.TerminalNotFoundException;
import org.openpos.tms.infra.PublicServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
	private AddressRepository addressRepository;

	@Autowired
	private OperationRepository operationRepository;

	@PostMapping("/terminals")
	@PublicServiceMethod
	public EntityModel<Terminal> createTerminalAction(@RequestBody(required = true) Terminal terminal) {
		addressRepository.save(terminal.getAddress());

		Terminal storedTerminal = terminalRepository.save(terminal);
		return getModel(storedTerminal);
	}

	@GetMapping("/terminals/{id}")
	@PublicServiceMethod
	public EntityModel<Terminal> getTerminalAction(@PathVariable UUID id) {
		Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new TerminalNotFoundException(id));
		return getModel(terminal);
	}

	@GetMapping("/terminals/{id}/operations")
	@PublicServiceMethod
	public List<EntityModel<Operation>> getTerminalOperationsAction(@PathVariable UUID id) {
		Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new TerminalNotFoundException(id));

		List<EntityModel<Operation>> ops = terminal.getOperations().stream().map(x -> this.getModel(terminal, x))
				.collect(Collectors.toList());

		return ops;
	}

	@PostMapping("/terminals/{id}/operations/{type}")
	@PublicServiceMethod
	public EntityModel<Operation> addTerminalOperationAction(@PathVariable UUID id, @PathVariable String type) {
		Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new TerminalNotFoundException(id));
		Operation op = operationRepository.findByType(type).orElseThrow(() -> new OperationNotFoundException(type));
		LOGGER.info("Adding operation {} to terminal {}", op.getType(), terminal.getTerminalId());
		terminal.addOperation(op);
		terminalRepository.save(terminal);
		return getModel(terminal, op);
	}

	@DeleteMapping("/terminals/{id}/operations/{type}")
	@PublicServiceMethod
	public void delTerminalOperationAction(@PathVariable UUID id, @PathVariable String type) {
		Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new TerminalNotFoundException(id));
		Operation op = operationRepository.findByType(type).orElseThrow(() -> new OperationNotFoundException(type));
		LOGGER.info("Removing operation {} from terminal {}", op.getType(), terminal.getTerminalId());
		terminal.removeOperation(op);
		terminalRepository.save(terminal);
	}

	@GetMapping("/terminals/{id}/operations/{type}")
	@PublicServiceMethod
	public EntityModel<Operation> getOperationByTypeAction(@PathVariable UUID id, @PathVariable String type) {
		Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new TerminalNotFoundException(id));
		Operation op = operationRepository.findByType(type).orElseThrow(() -> new OperationNotFoundException(type));
		return getModel(terminal, op);
	}

	private EntityModel<Terminal> getModel(Terminal terminal) {
		return EntityModel.of(terminal,
				linkTo(methodOn(TerminalController.class).getTerminalAction(terminal.getTerminalId())).withSelfRel(),
				linkTo(methodOn(TerminalController.class).getTerminalOperationsAction(terminal.getTerminalId()))
						.withRel("operations"));
	}

	private EntityModel<Operation> getModel(Terminal terminal, Operation operation) {
		return EntityModel.of(operation,
				linkTo(methodOn(TerminalController.class).getOperationByTypeAction(terminal.getTerminalId(),
						operation.getType())).withSelfRel(),
				linkTo(methodOn(TerminalController.class).getTerminalAction(terminal.getTerminalId()))
						.withRel("parent"));
	}

}
