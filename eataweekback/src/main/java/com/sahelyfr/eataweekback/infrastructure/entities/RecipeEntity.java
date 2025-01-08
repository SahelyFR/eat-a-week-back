package com.sahelyfr.eataweekback.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recipes")

public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "link")
    private String weblink;

    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<IngredientEntity> ingredients;

    @Column(name = "starting_month")
    private int startingMonth;

    @Column(name = "ending_month")
    private int endingMonth;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private String steps;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime lastUpdateDate;

    @Override
    public String toString() {
        return "RecipeEntity [id=" + id + ", name=" + name + ", weblink=" + weblink + ", image=" + image
                + ", ingredients="
                + ingredients + ", startingMonth=" + startingMonth + ", endingMonth=" + endingMonth + ", steps=" + steps
                + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + "]";
    }

}
