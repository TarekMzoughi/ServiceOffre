package com.iset.web;

import com.iset.dao.OffreRepository;
import com.iset.entities.Offre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Offres")
public class RestOffres {

    @Autowired
    private OffreRepository offreRepository;

    // 1️⃣ Afficher toutes les offres
    @GetMapping
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    // 2️⃣ Afficher une offre par son code (id)
    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id) {
        return offreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3️⃣ Ajouter une nouvelle offre
    @PostMapping
    public Offre addOffre(@RequestBody Offre offre) {
        return offreRepository.save(offre);
    }


    // 4️⃣ Modifier une offre existante
    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable Long id, @RequestBody Offre offreDetails) {
        return offreRepository.findById(id)
                .map(offre -> {
                    offre.setIntitule(offreDetails.getIntitule());
                    offre.setSpecialite(offreDetails.getSpecialite());
                    offre.setSociete(offreDetails.getSociete());
                    offre.setNbpostes(offreDetails.getNbpostes());
                    offre.setPays(offreDetails.getPays());
                    Offre updated = offreRepository.save(offre);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5️⃣ Supprimer une offre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        if (!offreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        offreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
