package com.app.web.config;
/*package com.cybotech.web.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cybotech.utility.constant.CommonConstants;
import com.cybotech.utility.propertyreader.PropertyReader;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Autowired
	@Qualifier(CommonConstants.QUERY_PROPERTY_READER)
	private PropertyReader propertyReader;

	@Bean
	public Docket api() {
		List<Parameter> newArrayList = new ArrayList<>(Arrays.asList((Parameter) new ParameterBuilder()
				.name(CommonConstants.X_AUTH_TOKEN).description(CommonConstants.REQUEST_VALIDATE)
				.modelRef(new ModelRef(CommonConstants.STRING_TXT)).parameterType(CommonConstants.HEADER_TXT).required(false).build()));

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().globalOperationParameters(newArrayList)
				.enable(Boolean.valueOf(propertyReader.getProperty(CommonConstants.SWAGGER_STATUS)));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
*/