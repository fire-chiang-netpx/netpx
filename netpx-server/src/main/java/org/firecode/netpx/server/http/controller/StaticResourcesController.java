package org.firecode.netpx.server.http.controller;

import org.firecode.netpx.server.http.support.MediaType;
import org.firecode.netpx.server.http.support.RequestMapping;
import org.firecode.netpx.server.http.support.RequestMappingHandler;
import org.firecode.netpx.server.http.support.RequestMethod;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Static resources controller（静态资源控制器）
 * @author ChiangFire
 */
@RequestMapping(uri="/static",method = RequestMethod.GET,produce = MediaType.TEXT_PLAIN_UTF8)
public class StaticResourcesController implements RequestMappingHandler<String> {

	@Override
	public String handler(FullHttpRequest req) throws Exception {
		
		return "我测试静态资源地址";
	}

}
