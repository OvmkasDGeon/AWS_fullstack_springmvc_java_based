package co.ovmkas.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

/*	@Override//한글 필터
	protected Filter[] getServletFilters() {
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		CharacterEncodingFilter filter = new CharacterEncodingFilter("utf-8", true);
		return new Filter[] {filter};
	}*/

	@Override
	protected void customizeRegistration(Dynamic registration) {
		MultipartConfigElement configElement  = new MultipartConfigElement("c:/upload/tmp");
		registration.setMultipartConfig(configElement);
	}

}
