package com.sahelyfr.eataweekback.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahelyfr.eataweekback.infrastructure.entities.MeasureEntity;

@Repository
public interface MeasureRepository extends JpaRepository<MeasureEntity, String> {

  Optional<MeasureEntity> findByShortName(String measureShortName);

}
