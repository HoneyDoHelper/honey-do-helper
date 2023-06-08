package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "chats")
public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "comment", length = 255)
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
    public Chats() {}

    public Chats(String comment, Tasks task, HoneyUsers user) {
        this.comment = comment;
        this.task = task;
        this.user = user;
    }

    public Chats(int id, String comment, Tasks task, HoneyUsers user) {
        this.id = id;
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

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {this.comment = comment;}

    public Tasks getTask() {
        return task;
    }
    public void setTask(Tasks task) {
        this.task = task;
    }

    public HoneyUsers getUser() {
        return user;
    }
    public void setUser(HoneyUsers user) {this.user = user;}
}
