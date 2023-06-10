package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "budgeted_duration", nullable = false)
    private int budgetedDuration;

    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Column(name = "is_completed", length = 15)
    private boolean isCompleted;

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

    public Tasks(String taskDetails, LocalDate dateAssigned, LocalTime timeStart, int budgetedDuration, LocalDate dateCompleted, String status, boolean isAccepted, HoneyUsers user, HoneydoerServices honeydoerService) {
        this.taskDetails = taskDetails;
        this.dateAssigned = dateAssigned;
        this.timeStart = timeStart;
        this.budgetedDuration = budgetedDuration;
        this.dateCompleted = dateCompleted;
        this.isCompleted = isCompleted;
        this.isAccepted = isAccepted;
        this.user = user;
        this.honeydoerService = honeydoerService;
    }

    public Tasks(int id, String taskDetails, LocalDate dateAssigned, LocalTime timeStart, int budgetedDuration, LocalDate dateCompleted, boolean isCompleted, boolean isAccepted, HoneyUsers user, HoneydoerServices honeydoerService) {
        this.id = id;
        this.taskDetails = taskDetails;
        this.dateAssigned = dateAssigned;
        this.timeStart = timeStart;
        this.budgetedDuration = budgetedDuration;
        this.dateCompleted = dateCompleted;
        this.isCompleted = isCompleted;
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

    public LocalTime getTimeStart() {return timeStart;}
    public void setTimeStart(LocalTime timeStart) {this.timeStart = timeStart;}

    public int getBudgetedDuration() {return budgetedDuration;}
    public void setBudgetedDuration(int budgetedDuration) {this.budgetedDuration = budgetedDuration;}

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }
    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }
    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public HoneyUsers getUser() {return user;}
    public void setUser(HoneyUsers user) {this.user = user;}

    public HoneydoerServices getHoneydoerService() {
        return honeydoerService;
    }
    public void setHoneydoerService(HoneydoerServices honeydoerService) {
        this.honeydoerService = honeydoerService;
    }



}
