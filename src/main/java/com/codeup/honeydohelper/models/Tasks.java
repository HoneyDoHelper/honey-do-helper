package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "task_details", nullable = false, length = 125)
    private String task_details;

    @Column(name = "date_assigned", nullable = false)
    private Date date_assigned;

    @Column(name = "date_completed")
    private Date date_completed;

    @Column(name = "status", length = 15)
    private String status;

    @Column(name = "is_accepted", nullable = false)
    private Boolean is_accepted;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "hd_service_id", nullable = false)
    private Integer hd_service_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask_details() {
        return task_details;
    }

    public void setTask_details(String task_details) {
        this.task_details = task_details;
    }

    public Date getDate_assigned() {
        return date_assigned;
    }

    public void setDate_assigned(Date date_assigned) {
        this.date_assigned = date_assigned;
    }

    public Date getDate_completed() {
        return date_completed;
    }

    public void setDate_completed(Date date_completed) {
        this.date_completed = date_completed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(Boolean is_accepted) {
        this.is_accepted = is_accepted;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getHd_service_id() {
        return hd_service_id;
    }

    public void setHd_service_id(Integer hd_service_id) {
        this.hd_service_id = hd_service_id;
    }


}
