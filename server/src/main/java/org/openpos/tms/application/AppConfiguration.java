package org.openpos.tms.application;

import org.springframework.context.annotation.Configuration;
// TODO this will come from the config server

@Configuration
public class AppConfiguration {
	// TODO for now this flag controls logging, need to check for an elegant
	// solution to block logging of PCI-sensitive data
	private boolean production;

	/**
	 * @return the production
	 */
	public boolean isProduction() {
		return production;
	}

	/**
	 * @param production the production to set
	 */
	public void setProduction(boolean production) {
		this.production = production;
	}

}
