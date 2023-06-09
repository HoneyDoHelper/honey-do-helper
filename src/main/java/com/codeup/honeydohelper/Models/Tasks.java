package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "task_details", nullable = false, length = 125)
    private String taskDetails;

    @Column(name = "date_assigned", nullable = false)
    private LocalDate dateAssigned;

    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Column(name = "status", length = 15)
    private String status;

    @Column(name = "is_accepted", nullable = false)
    private boolean isAccepted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private HoneyUsers user;

    @ManyToOne
    @JoinColumn(name = "honeydoer_service_id", nullable = false)
    private HoneydoerServices honeydoerService;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Tasks() {}

    public Tasks(String taskDetails, LocalDate dateAssigned, LocalDate dateCompleted, String status, boolean isAccepted, HoneyUsers user, HoneydoerServices honeydoerService) {
        this.taskDetails = taskDetails;
        this.dateAssigned = dateAssigned;
        this.dateCompleted = dateCompleted;
        this.status = status;
        this.isAccepted = isAccepted;
        this.user = user;
        this.honeydoerService = honeydoerService;
    }

    public Tasks(int id, String taskDetails, LocalDate dateAssigned, LocalDate dateCompleted, String status, boolean isAccepted, HoneyUsers user, HoneydoerServices honeydoerService) {
        this.id = id;
        this.taskDetails = taskDetails;
        this.dateAssigned = dateAssigned;
        this.dateCompleted = dateCompleted;
        this.status = status;
        this.isAccepted = isAccepted;
        this.user = user;
        this.honeydoerService = honeydoerService;
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

    public String getTaskDetails() {
        return taskDetails;
    }
    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public LocalDate getDateAssigned() {
        return dateAssigned;
    }
    public void setDateAssigned(LocalDate dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }
    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }
    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public HoneyUsers getUser() {
        return user;
    }
    public void setUser(HoneyUsers user) {
        this.user = user;
    }

    public HoneydoerServices getHoneydoerService() {
        return honeydoerService;
    }
    public void setHoneydoerService(HoneydoerServices honeydoerService) {
        this.honeydoerService = honeydoerService;
    }



}
