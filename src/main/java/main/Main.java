package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
/*
* TODO  validation (maybe in services)
* TODO  proper error display
* TODO  add "back" functionality
* TODO  proper session handling (current user, redirect unknown requests)
* TODO  tests!
* TODO  other TODOs from the code. some of them really need to be fixed
* */
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
