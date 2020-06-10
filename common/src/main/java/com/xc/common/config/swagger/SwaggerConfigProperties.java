package com.xc.common.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "swagger")
@Data
@Component
public class SwaggerConfigProperties {

	private boolean apiEnable;

	private boolean htmlEnable;

	private List<String> scanPackages;

	private List<Map<String, String>> apis;

	private String title;

	private String description;

	private String version;
}

