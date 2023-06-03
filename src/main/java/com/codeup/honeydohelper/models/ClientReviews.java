package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "client_reviews")
public class ClientReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "stars", nullable = false)
    private Enum stars;

    @Column(name = "comment")
    private String comment;

    @Column(name = "task_id", nullable = false)
    private Integer task_id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enum getStars() {
        return stars;
    }

    public void setStars(Enum stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
