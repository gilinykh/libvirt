package com.example.libvirtapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({LibvirtApiApplicationContext.class})
public class LibvirtApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibvirtApiApplication.class, args);
    }
}