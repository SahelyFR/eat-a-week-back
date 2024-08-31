package com.sahelyfr.eataweekback.repository;

import java.util.List;

import com.sahelyfr.eataweekback.model.Recette;

public interface RecetteRepositoryCustom {
    public List<Recette> findBySeason(String season);
}
