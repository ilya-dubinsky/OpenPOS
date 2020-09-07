package org.openpos.tms.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.openpos.tms.dao.PublicKeyRepository;
import org.openpos.tms.dao.dataobject.PublicKey;
import org.openpos.tms.errors.PublicKeyNotFoundException;
import org.openpos.tms.infra.crypto.PKUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyController extends BaseController {

	@Autowired
	private PublicKeyRepository publicKeyRepository;
	
	@Autowired
	private PKUtils pkUtils;

	@GetMapping("/publicKeys")
	public List<EntityModel<PublicKey>> getPublicKeyListAction() {
		return publicKeyRepository.findAll().stream().map(x->this.getModel(x)).collect(Collectors.toList());
	}

	@GetMapping("/publicKeys/{id}")
	public EntityModel<PublicKey> getPublicKeyAction(@PathVariable long id) {
		PublicKey key = publicKeyRepository.findById(id).orElseThrow(()->new PublicKeyNotFoundException(id));
		return getModel(key);
	}
	
	@PostMapping("/publicKeys")
	public EntityModel<PublicKey> addPublicKeyAction(@RequestBody PublicKey inputKey) {
		PublicKey key = new PublicKey();
		key.setKey(inputKey.getKey());
		key.setExpiryDate(inputKey.getExpiryDate());
		key.setFingerprint(pkUtils.getFingerprint(inputKey.getKey()));
		PublicKey savedKey = publicKeyRepository.save(key);
		return getModel(savedKey);
	}

	private EntityModel<PublicKey> getModel(PublicKey key) {
		return EntityModel.of(key, linkTo(methodOn(KeyController.class).getPublicKeyAction(key.getId())).withSelfRel());
	}
}
