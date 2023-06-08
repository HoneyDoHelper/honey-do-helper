package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "client_reviews")
public class ClientReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "stars", nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private HoneyUsers user;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public ClientReviews() {}

    public ClientReviews(Stars stars, String comment, Tasks task, HoneyUsers user) {
        this.stars = stars;
        this.comment = comment;
        this.task = task;
        this.user = user;
    }

    public ClientReviews(int id, Stars stars, String comment, Tasks task, HoneyUsers user) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.task = task;
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

    public Stars getStars() {
        return stars;
    }
    public void setStars(Stars stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Tasks getTask() {
        return task;
    }
    public void setTask(Tasks task) {
        this.task = task;
    }

    public HoneyUsers getUser() {
        return user;
    }
    public void setUser(HoneyUsers user) {
        this.user = user;
    }
}
