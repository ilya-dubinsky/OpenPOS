package org.openpos.tms.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.openpos.tms.controller.TransactionController;
import org.openpos.tms.protocol.message.Message;
import org.openpos.tms.protocol.message.POSMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class POSMessageModelAssembler extends RepresentationModelAssemblerSupport<Message, POSMessageModel> {

	public POSMessageModelAssembler() {
		super(TransactionController.class, POSMessageModel.class);
	}

	@Override
	public POSMessageModel toModel(Message entity) {
		POSMessageModel model = instantiateModel(entity);
		BeanUtils.copyProperties(entity, model);
		if (POSMessage.class.isAssignableFrom(entity.getClass())) {
			POSMessage posMessage = (POSMessage) entity;
			model.add(linkTo(methodOn(TransactionController.class).getTransaction(posMessage.getTransactionId()))
					.withSelfRel());

		}
		return model;
//		accountModel.add(linkTo(methodOn(AccountController.class).getAccountById(entity.getId())).withSelfRel());
//		accountModel.add(linkTo(methodOn(AccountController.class).getAllAccounts(null)).withRel("collection"));

	}

}
