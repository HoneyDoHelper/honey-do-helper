package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer_reviews")
public class HoneydoerReviews {

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
    @JoinColumn(name = "honeydoer_id", nullable = false)
    private Honeydoers honeydoer;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public HoneydoerReviews() {}

    public HoneydoerReviews(Stars stars, String comment, Tasks task, Honeydoers honeydoer) {
        this.stars = stars;
        this.comment = comment;
        this.task = task;
        this.honeydoer = honeydoer;
    }

    public HoneydoerReviews(int id, Stars stars, String comment, Tasks task, Honeydoers honeydoer) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.task = task;
        this.honeydoer = honeydoer;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public int getId() {return id;}
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

    public Honeydoers getHoneydoer() {
        return honeydoer;
    }
    public void setHoneydoer(Honeydoers honeydoer) {
        this.honeydoer = honeydoer;
    }
}
