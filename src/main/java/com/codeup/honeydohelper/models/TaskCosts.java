package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "task_costs")
public class TaskCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hd_pay", nullable = false)
    private Float hd_pay;

    @Column(name = "site_pay", nullable = false)
    private Float site_pay;

    @Column(name = "total_user_cost", nullable = false)
    private Float total_user_cost;

    @Column(name = "taxes", nullable = false)
    private Float taxes;

    @Column(name = "task_id", nullable = false)
    private Integer task_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getHd_pay() {
        return hd_pay;
    }

    public void setHd_pay(Float hd_pay) {
        this.hd_pay = hd_pay;
    }

    public Float getSite_pay() {
        return site_pay;
    }

    public void setSite_pay(Float site_pay) {
        this.site_pay = site_pay;
    }

    public Float getTotal_user_cost() {
        return total_user_cost;
    }

    public void setTotal_user_cost(Float total_user_cost) {
        this.total_user_cost = total_user_cost;
    }

    public Float getTaxes() {
        return taxes;
    }

    public void setTaxes(Float taxes) {
        this.taxes = taxes;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }
}
