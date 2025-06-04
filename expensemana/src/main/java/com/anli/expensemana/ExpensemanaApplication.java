package com.anli.expensemana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class ExpensemanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpensemanaApplication.class, args);
    }
}
