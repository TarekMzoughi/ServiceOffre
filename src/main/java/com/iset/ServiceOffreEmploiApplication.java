package com.iset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iset.dao.OffreRepository;
import com.iset.entities.Offre;

@SpringBootApplication
public class ServiceOffreEmploiApplication implements CommandLineRunner {

    @Autowired
    private OffreRepository offreRepository;

    public static void main(String[] args) {
        SpringApplication.run(ServiceOffreEmploiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Ajouter des offres d'emploi au démarrage
        offreRepository.save(new Offre("Web Design", "informatique", "AXA", 2, "France"));
        offreRepository.save(new Offre("Developpeur", "informatique", "Talys", 3, "Tunisie"));
        offreRepository.save(new Offre("Architecte", "informatique", "SIS", 2, "Allemagne"));
    }
}

