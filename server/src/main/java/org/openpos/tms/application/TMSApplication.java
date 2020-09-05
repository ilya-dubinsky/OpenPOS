package org.openpos.tms.application;

import org.openpos.tms.controller.TerminalController;
import org.openpos.tms.dao.TerminalRepository;
import org.openpos.tms.dao.dataobject.Terminal;
import org.openpos.tms.infra.audit.PublicServiceAuditAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // TODO remove to enable security
@EnableJpaRepositories("org.openpos.tms.dao")
@ComponentScan(basePackageClasses = { TerminalController.class, TMSApplication.class, TerminalRepository.class,
		PublicServiceAuditAspect.class })
@EntityScan(basePackageClasses = Terminal.class)
public class TMSApplication {

	// TODO: implement validation in REST controllers
	public static void main(String[] args) {
		SpringApplication.run(TMSApplication.class, args);
	}
}
