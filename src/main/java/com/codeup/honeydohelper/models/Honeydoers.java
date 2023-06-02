package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoers")
public class Honeydoers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @Column(name = "about_self", length = 255)
    private String about_self;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
