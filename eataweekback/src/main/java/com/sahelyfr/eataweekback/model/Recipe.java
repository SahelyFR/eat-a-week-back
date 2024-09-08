package com.sahelyfr.eataweekback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String name;
    @Column(name = "link")
    protected String weblink;
    protected String image;
    protected boolean spring;
    protected boolean summer;
    protected boolean autumn;
    protected boolean winter;

    public Recipe(){}

    private Recipe(String name, Builder builder){
        this.name = name;
        this.weblink = builder.weblink;
        this.image = builder.image;
        this.spring = builder.spring;
        this.summer = builder.summer;
        this.autumn = builder.autumn;
        this.winter = builder.winter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeblink() {
        return weblink;
    }

    public String getImage() {
        return image;
    }

    public boolean isSpring() {
        return spring;
    }

    public boolean isSummer() {
        return summer;
    }

    public boolean isAutumn() {
        return autumn;
    }

    public boolean isWinter() {
        return winter;
    }

    public static class Builder {
        private String weblink;
        private String image;
        private boolean spring;
        private boolean summer;
        private boolean autumn;
        private boolean winter;

        public Builder webLink(String weblink){
            // TODO check link conformity
            this.weblink = weblink;
            return this;
        }

        public Builder image(String image){
            // TODO check link conformity
            this.image = image;
            return this;
        }

        public Builder spring(boolean spring){
            this.spring = spring;
            return this;
        }

        public Builder summer(boolean summer){
            this.summer = summer;
            return this;
        }

        public Builder autumn(boolean autumn){
            this.autumn = autumn;
            return this;
        }

        public Builder winter(boolean winter){
            this.winter = winter;
            return this;
        }

        public Recipe build(String name){
            return new Recipe(name,this);
        }
    }
}
