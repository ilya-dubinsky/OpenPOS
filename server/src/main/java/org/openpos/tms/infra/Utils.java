package org.openpos.tms.infra;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	
	public static String toHexString(byte[] data) {
		if (null == data || data.length == 0) {
			LOGGER.warn("Invalid input to toHexString(): byte array null or empty");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (byte b : data)
			sb.append(String.format("%02X", b));

		return sb.toString();
	}
	
	public static String maskPan(String pan) {
		if (pan == null)
			return pan;
		return StringUtils.abbreviateMiddle(pan, "*", 10);
	}
}
