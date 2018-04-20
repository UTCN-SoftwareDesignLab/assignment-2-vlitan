package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.MultipartConfig;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@MultipartConfig
@ComponentScan
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
