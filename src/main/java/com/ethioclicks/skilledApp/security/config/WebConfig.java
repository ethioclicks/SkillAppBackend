//package com.example.demo.security.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//    }
//
////    @Bean
////    public FilterRegistrationBean corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.addAllowedOrigin("*");
////        config.addAllowedHeader("*");
////        config.addAllowedMethod("*");
////        source.registerCorsConfiguration("/**", config);
////        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////        bean.setOrder(0);
////        return bean;
////    }
//
////    @Bean
////    public CorsFilter corsFilter() {
////        System.out.println("cors filter initialized........");
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.addAllowedOrigin("http://localhost:4200/");
////        config.addAllowedHeader("*");
////        config.addAllowedMethod("OPTIONS");
////        config.addAllowedMethod("GET");
////        config.addAllowedMethod("POST");
////        config.addAllowedMethod("PUT");
////        config.addAllowedMethod("DELETE");
////        source.registerCorsConfiguration("/**", config);
////        return new CorsFilter(source);
////    }
//
//
//
//}
