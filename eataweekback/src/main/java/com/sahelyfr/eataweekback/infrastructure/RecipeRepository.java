package com.sahelyfr.eataweekback.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sahelyfr.eataweekback.infrastructure.entities.RecipeEntity;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

  @Query(nativeQuery = true, value = "SELECT re FROM recipes re WHERE re.startingMonth BETWEEN :startingMonth AND :endingMonth OR re.endingMonth BETWEEN :startingMonth AND :endingMonth")
  List<RecipeEntity> findBetweenMonths(@Param("startingMonth") int startingMonth,
      @Param("endingMonth") int endingMonth);

}
