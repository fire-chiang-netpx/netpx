package org.firecode.netpx.server.config;

import org.firecode.netpx.common.ConfigPropertyResolver;

/**
 * @author ChiangFire
 */
public class ServerProperties extends ConfigPropertyResolver {
	
	private static final ServerProperties serverProperties = new ServerProperties("server.properties");
	
	/**
	 * Server build port（netpx 绑定的端口）
	 */
	private Integer buildPort;
	
	/**
	 * Server build ip（netpx 绑定的ip）
	 */
	private String buildHost;
	
	/**
	 * Server build domain name（netpx 绑定的域名）
	 */
	private String buildName;
	
	/**
	 * Enabled admin manager（是否开启后台管理服务）
	 */
	private Boolean adminEnabled;
	/**
	 * Admin server domain name（后台管理服务域名）
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
