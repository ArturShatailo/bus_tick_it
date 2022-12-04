package com.tickets.tickets_managemet.util.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TicketConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public String paymentSystemAddress(){
        return "http://localhost:8083/api/pay";
    }

    @Bean
    public String paymentSystemSetStatus(){
        String statusController = "/status/{id}";
        return paymentSystemAddress() + statusController;
    }

    @Bean
    public String paymentSystemGetStatus(){
        String statusController = "/st/{id}";
        return paymentSystemAddress() + statusController;
    }

    @Bean
    public String paymentSystemPay(){
        String statusController = "/{amount}";
        return paymentSystemAddress() + statusController;
    }

}
