package com.example.dockercrudtorrexspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@SpringBootApplication
public class DockerCrudTorrexSpringApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        SpringApplication.run(DockerCrudTorrexSpringApplication.class, args);
    }
}
