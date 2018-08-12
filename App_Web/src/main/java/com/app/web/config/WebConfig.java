package com.app.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.app.util.constant.CommonConstants;
import com.app.util.dto.SearchDTO;
import com.app.web.config.interceptor.AuthenticationInterceptor;
import com.app.web.config.interceptor.AuthorizationInterceptor;
import com.app.web.config.interceptor.CORSInterceptor;

import ch.rasc.sse.eventbus.config.EnableSseEventBus;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableSseEventBus
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { CommonConstants.BASE_PACKAGE })
@PropertySource({ CommonConstants.SUCCESS_PROPERTIES,
		CommonConstants.APPLICATION_PROPERTIES_FILENAME })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment propertyReader;
	
	@Autowired
    private CloseableHttpClient httpClient;
 
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }
 
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
	
	/**
	 * Bean to handle Authentication Interceptor
	 * 
	 * @return AuthenticationInterceptor
	 */
	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}

	/**
	 * Bean to handle Authorization Interceptor
	 * 
	 * @return AuthorizationInterceptor
	 */
	@Bean
	public AuthorizationInterceptor authorizationInterceptor() {
		return new AuthorizationInterceptor();
	}

	// CommonsMultipartResolver
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxInMemorySize(50000000);
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	
	/**
	 * register interceptors
	 * 
	 * @param registry
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/v2/api-docs",
				"/configuration/ui", "/swagger-resources/**", CommonConstants.USER_URL + CommonConstants.TOKEN_CREATION,
				"/user/v1/search/**", "/download/getSteamingFile");
		registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**").excludePathPatterns("/v2/api-docs",
				"/configuration/ui", "/swagger-resources/**", CommonConstants.USER_URL + CommonConstants.TOKEN_CREATION,
				"/user/v1/search/**", "/download/getSteamingFile");
	}

	/**
	 * Bean to handle CORS Interceptor
	 * 
	 * @return CORSInterceptor
	 */
	@Bean
	public CORSInterceptor corsInterceptor() {
		return new CORSInterceptor();
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.ALL);
	}


	/**
	 * Swagger Configuration
	 * 
	 * @return
	 */
	@Bean
	@Autowired
	public Docket api() {
		List<Parameter> newArrayList = new ArrayList<>(Arrays.asList((Parameter) new ParameterBuilder()
				.name(CommonConstants.X_AUTH_TOKEN).description(CommonConstants.REQUEST_VALIDATE)
				.modelRef(new ModelRef(CommonConstants.STRING_TXT)).parameterType(CommonConstants.HEADER_TXT)
				.required(false).build()));

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().globalOperationParameters(newArrayList)
				.enable(Boolean.valueOf(propertyReader.getProperty(CommonConstants.SWAGGER_STATUS)));
	}

	/**
	 * Resource Handlers
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		bean.setBasename(CommonConstants.CLASSPATH_MESSAGES_PROPERTIES);
		bean.setDefaultEncoding("UTF-8");
		return bean;
	}

	@Bean
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public SearchDTO getSearchDTOBean() {
		return new SearchDTO();
	}
	/**/
}
