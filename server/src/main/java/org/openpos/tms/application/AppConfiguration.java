package org.openpos.tms.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
// TODO this will come from the config server

import lombok.Getter;
import lombok.Setter;

@Configuration
public class AppConfiguration {
	// TODO for now this flag controls logging, need to check for an elegant
	// solution to block logging of PCI-sensitive data
	@Getter
	@Setter
	private boolean production;

	@Getter
	@Setter
	@Value("${openpos.crypto.pk.hash}")
	private String pkHash;
}
