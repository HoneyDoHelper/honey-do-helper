package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "task_costs")
public class TaskCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "honeydoer_pay", nullable = false)
    private float honeydoerPay;

    @Column(name = "site_pay", nullable = false)
    private float sitePay;

    @Column(name = "total_user_cost", nullable = false)
    private float totalUserCost;

    @Column(name = "taxes", nullable = false)
    private float taxes;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Tasks task;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public TaskCosts() {}

    public TaskCosts(float honeydoerPay, float sitePay, float totalUserCost, float taxes, Tasks task) {
        this.honeydoerPay = honeydoerPay;
        this.sitePay = sitePay;
        this.totalUserCost = totalUserCost;
        this.taxes = taxes;
        this.task = task;
    }

    public TaskCosts(int id, float honeydoerPay, float sitePay, float totalUserCost, float taxes, Tasks task) {
        this.id = id;
        this.honeydoerPay = honeydoerPay;
        this.sitePay = sitePay;
        this.totalUserCost = totalUserCost;
        this.taxes = taxes;
        this.task = task;
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

    public float getHoneydoerPay() {
        return honeydoerPay;
    }
    public void setHoneydoerPay(float honeydoerPay) {
        this.honeydoerPay = honeydoerPay;
    }

    public float getSitePay() {
        return sitePay;
    }
    public void setSitePay(float sitePay) {
        this.sitePay = sitePay;
    }

    public float getTotalUserCost() {
        return totalUserCost;
    }
    public void setTotalUserCost(float totalUserCost) {
        this.totalUserCost = totalUserCost;
    }

    public float getTaxes() {
        return taxes;
    }
    public void setTaxes(float taxes) {
        this.taxes = taxes;
    }

    public Tasks getTask() {
        return task;
    }
    public void setTask(Tasks task) {
        this.task = task;
    }
}
