package org.firecode.netpx.server;

import java.io.File;

import org.firecode.netpx.common.ConfigPropertyResolver;
import org.firecode.netpx.server.config.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * @author ChiangFire
 */
public class Application {

	static final boolean SSL = System.getProperty("ssl") != null;
	static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "8080"));

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		ServerProperties serverProperties = ServerProperties.getInstance();
		loadLogbackConfig();
		// Configure SSL.
		final SslContext sslCtx;
		if (SSL) {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslCtx = null;
		}

		// Configure the server.
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new DispatcherServerInitializer(sslCtx));
			Channel ch = b.bind(PORT).sync().channel();
			LOG.info("Proxy server started on domain \"{}\" port(s) {}",serverProperties.getBuildName(),serverProperties.getBuildPort());
			if(serverProperties.getAdminEnabled()) {
				LOG.info("Admin server started on domain \"{}\" port(s) {}",serverProperties.getAdminDomain(),serverProperties.getBuildPort());
			}
			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	/**
	 * load config logback.xml
	 * 
	 * @throws JoranException
	 */
	private static void loadLogbackConfig() throws JoranException {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator loggerConfigurator = new JoranConfigurator();
		loggerConfigurator.setContext(loggerContext);
		loggerContext.reset();
		loggerConfigurator.doConfigure(Thread.currentThread().getContextClassLoader().getResource(String.join(File.separator, ConfigPropertyResolver.CONFIG_FOLDER, "logback.xml")));
	}
}
