package com.xc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 权限相关的zuul过滤器，type为PRE_TYPE
 *
 * @author shaper 2021/3/2 16:08
 */
public class AuthorizedRequestFilter extends ZuulFilter {

	@Override
	public String filterType() {
		// 前置的过滤器：请求发送之前进行过滤的
		// 必须设置请求头信息，才能进行访问
		// 还有另外三种类型：error、post、route
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;	// 是否开启过滤服务
	}

	/**
	 * 有些服务需要security认证
	 *
	 * @author shaper 2020/11/11 15:19
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext() ; // 获取当前请求的上下文
		String auth = "admin:123456"; // 认证的原始信息
		byte[] encodedAuth = Base64.getEncoder()
				.encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
		String authHeader = "Basic " + new String(encodedAuth);
		currentContext.addZuulRequestHeader("Authorization", authHeader);
		return null;
	}
}
