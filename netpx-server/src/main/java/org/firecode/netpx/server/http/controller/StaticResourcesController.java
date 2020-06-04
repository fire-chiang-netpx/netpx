package org.firecode.netpx.server.http.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.firecode.netpx.server.Server;
import org.firecode.netpx.server.http.PathMatchingResourceClassResolver;
import org.firecode.netpx.server.http.support.RequestMapping;
import org.firecode.netpx.server.http.support.RequestMappingHandler;
import org.firecode.netpx.server.http.support.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Static resources controller（静态资源控制器）
 * @author ChiangFire
 */
@RequestMapping(urls={StaticResourcesController.ROOT_STATIC_RESOURCES,PathMatchingResourceClassResolver.DEFAULT_PATH_SEPARATOR},method = RequestMethod.GET)
public class StaticResourcesController implements RequestMappingHandler<String> {
	
	private static final Logger LOG = LoggerFactory.getLogger(StaticResourcesController.class);
	
	public static final String ROOT_STATIC_RESOURCES = "/static";
	
	private static final String STATIC_RESOURCES_DIR = "webapp";
	
	private static final String INDEX_HTML_URI = STATIC_RESOURCES_DIR + "/index.html";
	
	private static final ClassLoader SERVER_CLASS_LOADER = Server.class.getClassLoader();
	
	private static final LoadingCache<String, String> RESOURCES_CACHE = CacheBuilder.newBuilder().maximumSize(150).expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<String,String>() {
        public String load(String uri) throws Exception {
    		try(InputStream resourceInputStream = SERVER_CLASS_LOADER.getResourceAsStream(uri);){
    			if(null != resourceInputStream) {
    				try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceInputStream));) {
    					String res = bufferedReader.lines().collect(Collectors.joining());
    					return res;
    				}
    			}
    		} catch(Exception e) {
    			LOG.error("Static resources '"+uri+"' load fail", e);
    		}
    		return null;
        }
    });
	
	@Override
	public String handler(FullHttpRequest req) throws Exception {
		String uri = req.uri().replace(ROOT_STATIC_RESOURCES,STATIC_RESOURCES_DIR);
		if(PathMatchingResourceClassResolver.DEFAULT_PATH_SEPARATOR.equals(uri) || STATIC_RESOURCES_DIR.equals(uri)) {
			uri = INDEX_HTML_URI;
		}
		return RESOURCES_CACHE.get(uri);
	}
}
