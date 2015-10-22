package com.knightNote.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * Created by knight on 15/10/22.
 */
@Configuration
public class KnightConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public ObjectMapper cacheManager() {
        return objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }


}
