package com.xc.gateway.feignclient.fallback;

import com.xc.gateway.feignclient.CommonFeignClient;
import org.springframework.stereotype.Component;

@Component
public class CommonFeignClientFallBack implements CommonFeignClient {

	@Override
	public void initCommonData() {
		System.out.println("============= 初始化数据成功  ==============");
	}
}
