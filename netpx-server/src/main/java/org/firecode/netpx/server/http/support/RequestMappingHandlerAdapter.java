package org.firecode.netpx.server.http.support;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.Objects;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author ChiangFire
 */
public class RequestMappingHandlerAdapter {
	
	private String uri;
	
	private MediaType mediaType;
	
	private RequestMethod requestMethod;
	
	private RequestMappingHandler<?> requestMappingHandler;
	
	public RequestMappingHandlerAdapter(String uri,RequestMethod requestMethod,MediaType mediaType,RequestMappingHandler<?> requestMappingHandler) {
		this.uri = uri;
		this.requestMethod = requestMethod;
		this.mediaType = mediaType;
		this.requestMappingHandler = requestMappingHandler;
	}
	
	/**
	 * @param ctx
	 * @param req
	 */
	public FullHttpResponse handler(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
		ByteBuf content = null;
		if(req.method().equals(requestMethod.value())) {
			content = ctx.alloc().buffer();
			Object res = this.requestMappingHandler.handler(req);
			if(!Objects.isNull(res)) {
				if(MediaType.TEXT_PLAIN_UTF8.equals(mediaType)) {
					ByteBufUtil.writeUtf8(content, res.toString());
				}
				if(MediaType.APPLICATION_JSON_UTF8.equals(mediaType)) {
					ByteBufUtil.writeUtf8(content,JSON.toJSONString(res));
				}
			}
		}
		if(content == null) {
			content = Unpooled.EMPTY_BUFFER;
		}
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
        response.headers().set(CONTENT_TYPE,mediaType.value());
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
	}

	public String getUri() {
		return uri;
	}
}
