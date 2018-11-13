package com.xykj.omadmin;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xykj"})
//@EntityScan("com.xykj.omservice")
@EnableJpaRepositories("com.xykj.omservice")
public class OmAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmAdminApplication.class, args);
    }


    /**
     * 设置上传文件大小，配置文件属性设置无效
     * @date 2018年3月8日
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("9000MB");
        config.setMaxRequestSize("9000MB");
        return config.createMultipartConfig();
    }


//    /**
//     * 解决前端跨域
//     * @return
//     */
//    @Bean
//    public CorsFilter corsFilter(){
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);  // 允许cookies跨域
//        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
//        config.addAllowedOrigin("*");  // 允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("OPTIONS");// 允许提交请求的方法，*表示全部允许
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");// 允许Get的请求方法
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    /*配置http自动转为https*/
//    @Bean
//    public ServletWebServerFactory servletWebServerFactory(){
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");//机密的
//                SecurityCollection securityCollection = new SecurityCollection();
//                securityCollection.addPattern("/*");
//                securityConstraint.addCollection(securityCollection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        factory.addAdditionalTomcatConnectors(httpConnector());
//        return factory;
//    }
//
//    /**
//     * 这里配置将http 8086端口请求
//     * 转发到https 的 8888端口
//     * @return
//     */
//    @Bean
//    public Connector httpConnector(){
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8086);
//        connector.setSecure(false);
//        connector.setRedirectPort(8090);
//        return  connector;
//    }


}
