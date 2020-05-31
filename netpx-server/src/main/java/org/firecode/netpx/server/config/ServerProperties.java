package org.firecode.netpx.server.config;

import org.firecode.netpx.common.ConfigPropertyResolver;

/**
 * @author ChiangFire
 */
public class ServerProperties extends ConfigPropertyResolver {
	
	private static final ServerProperties serverProperties = new ServerProperties("server.properties");
	
	/**
	 * Server build port
	 */
	private Integer buildPort;
	
	/**
	 * Server build ip
	 */
	private String buildHost;
	
	/**
	 * Server build domain name
	 */
	private String buildName;
	
	/**
	 * Enabled admin manager
	 */
	private Boolean adminEnabled;
	/**
	 * Admin server domain name
	 */
	private String adminDomain;
	
	public Integer getBuildPort() {
		return buildPort;
	}
	
	public String getBuildHost() {
		return buildHost;
	}

	public String getBuildName() {
		return buildName;
	}

	public Boolean getAdminEnabled() {
		return adminEnabled;
	}
	
	public String getAdminDomain() {
		return adminDomain;
	}

	private ServerProperties(String configFileName) {
		super(configFileName);
		adminEnabled = getBoolean("server.admin.enabled");
		buildPort = getInteger("server.build.port");
		buildHost = getString("server.build.host");
		buildName = getString("server.build.name");
		adminDomain = getString("server.admin.build.name");
	}
	
	public static final ServerProperties getInstance() {
		return serverProperties;
	}
}
