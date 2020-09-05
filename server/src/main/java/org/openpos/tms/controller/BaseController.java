package org.openpos.tms.controller;

import org.openpos.tms.application.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {
	@Autowired
	protected AppConfiguration appConfiguration;

	@Autowired
	protected ApplicationContext applicationContext;

}
