package com.apress.crm.customer.Config;

import static java.lang.System.out;
import java.util.UUID;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.crm.customer.Domain.Customer;
import com.apress.crm.customer.Interface.Repository;

@Configuration 
public class CustomerConfiguration {
    
    @Bean
    ApplicationListener<ApplicationReadyEvent> customerAppReady(Repository<Customer, UUID> customerRepository) {
        return event -> {
            customerRepository.save(new Customer("Dylan", "dylanc@abc.com", "1-800-Phone"));
            customerRepository.save(new Customer("Kimberly", "kimc@abc.com", "1-801-Phone"));
            customerRepository.save(new Customer("Levi", "levi@abc.com", "1-802-Phone"));
            customerRepository.save(new Customer("Magnolia", "maggie@abc.com", "1-803-Phone"));
            customerRepository.findAll().forEach(out::println);
        };
    }
}
