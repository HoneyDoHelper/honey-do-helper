package com.codeup.honeydohelper.models;

import jakarta.persistence.*;
import org.w3c.dom.Text;

@Entity
@Table(name = "honeydoer_reviews")
public class HoneydoerReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "stars", nullable = false)
    private Enum stars;

    @Column(name = "comment")
    private String comment;

    @Column(name = "task_id", nullable = false)
    private Integer task;

    @Column(name = "hd_id", nullable = false)
    private Integer hd_id;

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

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public Integer getHd_id() {
        return hd_id;
    }

    public void setHd_id(Integer hd_id) {
        this.hd_id = hd_id;
    }
}
