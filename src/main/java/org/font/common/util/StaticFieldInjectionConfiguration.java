package org.font.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 工具类的注入
 * 
 * @author  llsmp
 * 
 */
@Component
public class StaticFieldInjectionConfiguration {

    @Autowired
    MessageSource resources;

    @PostConstruct
    private void init() {
    	System.out.println("\n\n-----StaticFieldInjectionConfiguration----");
		CheckUtil.setResources(resources);
    }
}