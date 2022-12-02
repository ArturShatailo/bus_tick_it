package com.tickets.payment_system.util.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class StatusConfig {

    @Bean
    public List<String> status(){
        return List.of("NEW", "FAIL", "DONE");
    }


}
