package org.hk.compass.config;

import lombok.extern.slf4j.Slf4j;
import org.hk.compass.filter.CrossOriginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengry
 * @description
 * @since 2019/12/30
 */
//@Slf4j
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public FilterRegistrationBean crossOriginFilter(){
//        log.info("******** crossOriginFilter ***************");
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        CrossOriginFilter filter = new CrossOriginFilter();
//        filterRegistrationBean.setFilter(filter);
//
//        return filterRegistrationBean;
//    }
//}
