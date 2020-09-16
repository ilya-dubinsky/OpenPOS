package org.openpos.tms.application;

import org.apache.tomcat.util.net.openssl.ciphers.Protocol;
import org.openpos.tms.controller.TerminalController;
import org.openpos.tms.controller.handler.AbstractTransactionHandler;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.TerminalDO;
import org.openpos.tms.infra.audit.PublicServiceAuditAspect;
import org.openpos.tms.infra.crypto.PKUtils;
import org.openpos.tms.model.AccountModel;
import org.openpos.tms.protocol.impl.DefaultProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // TODO remove to enable security
@EnableJpaRepositories("org.openpos.tms.dao")
@ComponentScan(basePackageClasses = { TerminalController.class, TMSApplication.class, TerminalRepository.class,
		PublicServiceAuditAspect.class, PKUtils.class, AccountModel.class, DefaultProtocol.class, Protocol.class,
		AbstractTransactionHandler.class })
@EntityScan(basePackageClasses = TerminalDO.class)
public class TMSApplication {

	// TODO: implement validation in REST controllers
	public static void main(String[] args) {
		SpringApplication.run(TMSApplication.class, args);
	}

}
