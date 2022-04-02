package com.sahelyfr.eataweekback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "recettes")
public class Recette {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    private String link;
    private boolean spring;
    private boolean summer;
    private boolean autumn;
    private boolean winter;
}
