package com.johnfnash.learn.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		if(applicationContext.containsBean(beanName)) {
			return (T) applicationContext.getBean(beanName);
		} else {
			return null;
		}
	}
	
	public static <T> Map<String, T> getBeansOfType(Class<T> baseType){
        return applicationContext.getBeansOfType(baseType);
    }

	public static boolean isNotBlank(String str) {
		boolean result = true;
		if(null == str || "".equals(str.trim())) {
			result = false;
		}
		return result;
	}
	
}
