package com.sahelyfr.eataweekback.repository;

import com.sahelyfr.eataweekback.model.Recette;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long>, RecetteRepositoryCustom {

}
