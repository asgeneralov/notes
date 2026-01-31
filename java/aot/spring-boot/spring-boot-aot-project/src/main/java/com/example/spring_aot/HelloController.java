package com.example.spring_aot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot with Embedded Tomcat!";
    }
    
    @GetMapping("/")
    public String home() {
        return "Spring Boot application running with embedded Tomcat";
    }
}
