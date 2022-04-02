package com.sahelyfr.eataweekback.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sahelyfr.eataweekback.model.Recette;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class RecetteRepositoryImpl implements RecetteRepositoryCustom {
    
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Iterable<Recette> findBySeason(String season){
        String queryString = "SELECT re.* FROM recettes re where "+season+" = true";
        Query query = entityManager.createNativeQuery(queryString, Recette.class);

        return query.getResultList();
    }
}
