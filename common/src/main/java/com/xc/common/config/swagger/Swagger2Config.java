package com.xc.common.config.swagger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Autowired
	private SwaggerConfigProperties properties;

	@Bean
	public Docket createRestApi(){
		String scanPackages = this.properties.getScanPackages().stream().collect(Collectors.joining(","));
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.enable(this.properties.isApiEnable()).apiInfo(this.apiInfo());
		docket.select().apis(basePackage(scanPackages)).paths(PathSelectors.any()).build();
		return docket;
	}

	private Predicate<RequestHandler> basePackage(String basePackage) {
		return (input)->{
			return (Boolean)declaringClass(input).transform(handlePackage(basePackage)).or(true);
		};
	}

	private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
		return Optional.fromNullable(input.declaringClass());
	}

	private static Function<Class<?>, Boolean> handlePackage(String basePackage) {
		return (input)->{
			String [] arr = basePackage.split(",");
			for (int i = 0; i < arr.length; ++ i) {
				String strPackage = arr[i];
				boolean isMatch = input.getPackage().getName().startsWith(strPackage);
				if(isMatch){
					return true;
				}
			}
			return false;
		};
	}

	private ApiInfo apiInfo() {
		ApiInfo info = new ApiInfoBuilder()
				.title(this.properties.getTitle())
				.description(this.properties.getDescription())
				.version(this.properties.getVersion()).build();
		return info;
	}


}
