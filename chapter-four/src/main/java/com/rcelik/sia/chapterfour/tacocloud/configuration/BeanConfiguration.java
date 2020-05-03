package com.rcelik.sia.chapterfour.tacocloud.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfiguration implements WebMvcConfigurer {

//    @Bean
//    ServletRegistrationBean h2ServletRegistration(){
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean( new WebServlet());
//        servletRegistrationBean.addUrlMappings("/console/*");
//        return servletRegistrationBean;
//    }


    // home view ini olusturmak icin
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
