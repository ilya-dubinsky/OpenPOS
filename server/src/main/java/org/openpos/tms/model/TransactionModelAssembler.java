package org.openpos.tms.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.openpos.tms.controller.TransactionController;
import org.openpos.tms.dao.dataobject.TransactionDO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TransactionModelAssembler extends RepresentationModelAssemblerSupport<TransactionDO, TransactionModel> {

	public TransactionModelAssembler() {
		super(TransactionController.class, TransactionModel.class);
	}

	@Override
	public TransactionModel toModel(TransactionDO entity) {
		TransactionModel model = instantiateModel(entity);
		
		BeanUtils.copyProperties(entity, model, "currency");
		model.setCurrency(entity.getCurrency().getAlpha3());
		model.add(linkTo(methodOn(TransactionController.class).getTransaction(entity.getId()))
				.withSelfRel());
		return model;
	}
}
