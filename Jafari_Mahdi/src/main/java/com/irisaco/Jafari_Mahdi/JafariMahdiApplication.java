package com.irisaco.Jafari_Mahdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// *** NOTE: This project is dependent on an external MySQL database
@SpringBootApplication
public class JafariMahdiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JafariMahdiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

