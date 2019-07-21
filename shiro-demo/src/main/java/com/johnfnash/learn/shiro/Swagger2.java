package com.johnfnash.learn.shiro;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Resource
	private TypeResolver typeResolver;
	
	@Value("${apiDoc.enable:false}")
	private boolean apiDocEnable;

	@Bean
	public Docket createRestApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.johnfnash.learn.shiro"))
				.paths(PathSelectors.any())
				.build()
				.directModelSubstitute(LocalDate.class, String.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
								typeResolver.resolve(WildcardType.class)))
				.genericModelSubstitutes(ResponseEntity.class);
		return docket.enable(apiDocEnable);
	}

	// 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("shiro demo")
				// 创建人
				.contact(new Contact("xxx", "", ""))
				// 版本号
				.version("1.0")
				// 描述
				.description("API 描述").build();
	}
}
