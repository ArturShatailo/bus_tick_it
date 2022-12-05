package com.tickets.payment_system.util.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class StatusConfig {

    /**
    This Bean returns a List of statuses for random
     **/
    @Bean
    public List<String> status(){
        return List.of("NEW", "FAIL", "DONE");
    }


}
