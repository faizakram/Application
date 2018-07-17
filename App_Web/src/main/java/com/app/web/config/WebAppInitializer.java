package com.app.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.app.util.constant.CommonConstants;
import com.app.web.config.sql.MySqlDbConfig;

public class WebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		  AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(ctx);
	        servletContext.addListener(contextLoaderListener);
	        ctx.register(WebConfig.class);
	        ctx.register(MySqlDbConfig.class);
	        ctx.register(QuartzConfig.class);
	        ctx.register(RedisConfig.class);
	        //ctx.setServletContext(servletContext);
	        ServletRegistration.Dynamic servlet = servletContext.addServlet(CommonConstants.DISPATCHER, new DispatcherServlet(ctx));
	        servlet.setLoadOnStartup(CommonConstants.ONE);
	        servlet.addMapping(CommonConstants.SLAH);
	        // For CORS Pre Filght Request
	        servlet.setAsyncSupported(true);
	        servlet.setInitParameter(CommonConstants.DISPATCHOPTIONSREQUEST, CommonConstants.TRUETXT);
		
	}

}
