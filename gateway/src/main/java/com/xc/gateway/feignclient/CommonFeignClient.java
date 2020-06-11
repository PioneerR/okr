package com.xc.gateway.feignclient;

import com.xc.gateway.feignclient.fallback.CommonFeignClientFallBack;
import com.xc.project.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${feignclient.common.service-id}",
			fallback = CommonFeignClientFallBack.class,
			configuration = {FeignConfiguration.class})
public interface CommonFeignClient {

	@PostMapping("/init-common-data")
	void initCommonData();



}
