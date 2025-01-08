package com.sahelyfr.eataweekback.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ingredients")
public class IngredientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonSerialize(using = ToStringSerializer.class)
  private int id;

  private String name;

  private boolean enabled;

  @ManyToOne
  private MeasureEntity measure;

  @ManyToMany(mappedBy = "ingredients")
  List<RecipeEntity> recipes;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime creationDate;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime lastUpdateDate;

}
