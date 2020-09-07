package org.openpos.tms.infra.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.openpos.tms.application.AppConfiguration;
import org.openpos.tms.infra.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PKUtils {
	
	@Autowired
	private AppConfiguration appConfiguration;
	
	public String getFingerprint(byte []data ) {
		if (data == null )
		{
			LOGGER.warn("Invalid key passed to getFingerprint: null");
			return "";
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(appConfiguration.getPkHash());
			byte[] encodedhash = digest.digest(data);
			return Utils.toHexString(encodedhash);
			
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("No PK hashing algorithm found for name {}", appConfiguration.getPkHash());
			return "";
		}
	}
}
