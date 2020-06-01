package org.firecode.netpx.server.http.support;
/**
 * @author ChiangFire
 */
public enum MediaType {
	
	TEXT_PLAIN_UTF8("text/plain;charset=UTF-8"),
	
	APPLICATION_JSON_UTF8("application/json;charset=UTF-8");
	
	private String value;
	
	MediaType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
