package com.wh.foo.core;

import com.wh.foo.interceptor.MenuInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: Interceptor配置
 * @Auther: WangHong
 * @Date: 2020/4/07 11:47
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

	/**
	 * 配置拦截器
	 * @author lichao
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("=============addInterceptors=================");
		registry.addInterceptor(new MenuInterceptor()).addPathPatterns("/**");
	}
}
