package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer")
public class Honeydoer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @Column(name = "about_self")
    private String about_self;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getAbout_self() {
        return about_self;
    }

    public void setAbout_self(String about_self) {
        this.about_self = about_self;
    }
}
