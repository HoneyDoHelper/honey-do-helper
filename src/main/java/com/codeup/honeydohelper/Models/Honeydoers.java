package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoers")
public class Honeydoers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "rating", nullable = false)
    private float rating;

    @Column(name = "about_self")
    private String aboutSelf;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Honeydoers() {}

    public Honeydoers(float rating, String aboutSelf, Users user) {
        this.rating = rating;
        this.aboutSelf = aboutSelf;
        this.user = user;
    }

    public Honeydoers(int id, float rating, String aboutSelf, Users user) {
        this.id = id;
        this.rating = rating;
        this.aboutSelf = aboutSelf;
        this.user = user;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAboutSelf() {
        return aboutSelf;
    }
    public void setAboutSelf(String aboutSelf) {
        this.aboutSelf = aboutSelf;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
}
