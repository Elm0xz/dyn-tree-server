package com.pretz.dyntreeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class App {

    public static void main (String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(App.class, args);
/*        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String bean : beanNames) {
            System.out.println(bean);
        }*/
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
