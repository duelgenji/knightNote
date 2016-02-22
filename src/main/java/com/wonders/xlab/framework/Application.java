// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.wonders.xlab.framework;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.thetransactioncompany.cors.CORSFilter;
import com.wonders.xlab.framework.repository.MyRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = {"${app.basePackages}"},
        repositoryFactoryBeanClass = MyRepositoryFactoryBean.class
)
@EnableScheduling
@EntityScan({"${app.basePackages}"})
@ComponentScan({"${app.basePackages}"})
public class Application extends SpringBootServletInitializer {
    @Value("${jackson.indent.output}")
    private boolean jacksonIndentOutput = false;
    @Value("${cors.allowOrigin}")
    private String corsAllowOrigin = "*";

    public Application() {
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        HashMap initParameters = new HashMap();
        initParameters.put("cors.allowOrigin", this.corsAllowOrigin);
        initParameters.put("cors.supportedMethods", "GET, POST, HEAD, PUT, DELETE");
        initParameters.put("cors.supportedHeaders", "Accept, Origin, X-Requested-With, Content-Type, Last-Modified, token ,accessToken");
        CORSFilter filter = new CORSFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setInitParameters(initParameters);
        filterRegistrationBean.addUrlPatterns(new String[]{"/*"});
        return filterRegistrationBean;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        if(this.jacksonIndentOutput) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Hibernate4Module hibernateMoudle = new Hibernate4Module();
        hibernateMoudle.disable(Feature.USE_TRANSIENT_ANNOTATION);
        return objectMapper.registerModule(new JodaModule()).registerModule(hibernateMoudle);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.profiles(new String[]{"production"}).sources(new Class[]{Application.class});
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
