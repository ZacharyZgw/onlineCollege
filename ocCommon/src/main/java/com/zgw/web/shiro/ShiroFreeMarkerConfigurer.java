package com.zgw.web.shiro;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.zgw.web.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
 * shiro freemarker 整合
 */
public class ShiroFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
		this.getConfiguration().setNumberFormat("#");
	}
}
