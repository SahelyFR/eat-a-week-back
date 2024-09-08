package com.sahelyfr.eataweekback.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.sahelyfr.eataweekback.model.Recipe;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Recipe> findBySeason(String season) {
        String queryString = "SELECT re.* FROM recettes re where " + season + " = true";
        Query query = entityManager.createNativeQuery(queryString, Recipe.class);
        return query.getResultList();
    }
}
