package com.diversidade.inclusao;

import com.diversidade.inclusao.entity.Candidato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal para iniciar a aplicação Spring Boot.
 */
@SpringBootApplication
public class InclusaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(InclusaoApplication.class, args);
    }
}