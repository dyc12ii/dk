package com.dyc12ii.dk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * author: dingyanchao
 * date: 2019/3/4 20:18
 * desc:
 */
@SpringBootApplication
public class Application /*extends WebMvcConfigurerAdapter*/ {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${dk.web.resource.location}")
    private String resourceLocation;



    @Bean
    RestTemplate restTemplate(
            /*BaseCoreDependenciesCollector dependencies,*/
            @Value("${base.restTemplate.connectionTimeout}") int connectionTimeout,
            @Value("${base.restTemplate.readTimeout}") int readTimeout,
            @Value("${base.restTemplate.writeTimeout}") int writeTimeout) {

        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setWriteTimeout(writeTimeout);

        RestTemplate template = new RestTemplate(requestFactory);
        /*if (template.getMessageConverters().removeIf(c -> c instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter)) {
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }*/
        /*if (dependencies.getClientHttpRequestInterceptors() != null) {
            template.getInterceptors().addAll(dependencies.getClientHttpRequestInterceptors());
        }*/

        return template;
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static*//**") //静态资源访问 uri
                .addResourceLocations(resourceLocation);  //静态资源实际位置
    }*/
}
