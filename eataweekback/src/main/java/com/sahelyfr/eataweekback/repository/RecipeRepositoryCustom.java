package com.sahelyfr.eataweekback.repository;

import java.util.List;

import com.sahelyfr.eataweekback.model.Recipe;

public interface RecipeRepositoryCustom {
    public List<Recipe> findBySeason(String season);
}
