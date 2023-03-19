package com.sahelyfr.eataweekback.service;

import java.util.List;
import java.util.Optional;

import com.sahelyfr.eataweekback.model.Recette;
import com.sahelyfr.eataweekback.repository.RecetteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class RecetteService {

    @Autowired
    private RecetteRepository rr;

    public Optional<Recette> getRecette(final Long id) {
        return rr.findById(id);
    }

    public List<Recette> getAllRecettes() {
        return rr.findAll();
    }

    public void deleteRecette(final Long id) {
        rr.deleteById(id);
    }

    public Recette saveRecette(Recette re) {
        Recette savedRecette = rr.save(re);
        return savedRecette;
    }

    public List<Recette> getRecettesBySeason(String season) {
        return rr.findBySeason(season);
    }
}
