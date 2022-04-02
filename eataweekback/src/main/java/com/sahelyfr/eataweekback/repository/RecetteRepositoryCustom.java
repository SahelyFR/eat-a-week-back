package com.sahelyfr.eataweekback.repository;

import com.sahelyfr.eataweekback.model.Recette;

public interface RecetteRepositoryCustom {
    public Iterable<Recette> findBySeason(String season);
}
