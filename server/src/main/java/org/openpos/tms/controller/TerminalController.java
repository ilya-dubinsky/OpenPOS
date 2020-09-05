package org.openpos.tms.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import org.openpos.tms.dao.AddressRepository;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.Terminal;
import org.openpos.tms.errors.TerminalNotFoundException;
import org.openpos.tms.infra.PublicServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerminalController extends BaseController {
	@Autowired
	private TerminalRepository terminalRepository;

	@Autowired
	private AddressRepository addressRepository;

	@PostMapping("/terminals")
	@PublicServiceMethod
	public EntityModel<Terminal> createTerminalAction(@RequestBody(required = true) Terminal terminal) {
		addressRepository.save(terminal.getAddress());

		Terminal storedTerminal = terminalRepository.save(terminal);
		return EntityModel.of(storedTerminal,
				linkTo(methodOn(TerminalController.class).getTerminalAction(storedTerminal.getTerminalId()))
						.withSelfRel());
	}

	@GetMapping("/terminals/{id}")
	@PublicServiceMethod
	public EntityModel<Terminal> getTerminalAction(@PathVariable UUID id) {
		String strId = String.valueOf(id);
		Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new TerminalNotFoundException(strId));
		return EntityModel.of(terminal, linkTo(methodOn(TerminalController.class).getTerminalAction(id)).withSelfRel());
	}

}
