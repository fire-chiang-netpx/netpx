package org.firecode.netpx.server.http.support;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
/**
 * @author ChiangFire
 */
public interface RequestMappingHandler {
	
	ByteBuf handler(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception;
}
