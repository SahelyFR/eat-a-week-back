package com.sahelyfr.eataweekback.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "measures")
public class MeasureEntity {

  @Id
  @Column(name = "short_name")
  private String shortName;

  private String name;

  private boolean enabled;

  @OneToMany(mappedBy = "measure")
  private List<IngredientEntity> ingredients;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime creationDate;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime lastUpdateDate;

}
