package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer_services")
public class HoneydoerServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rate", nullable = false)
    private Float rate;

    @Column(name = "about_service", nullable = false, length = 255)
    private String about_service;

    @Column(name = "hd_id", nullable = false)
    private Integer hd_id;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getAbout_service() {
        return about_service;
    }

    public void setAbout_service(String about_service) {
        this.about_service = about_service;
    }

    public Integer getHd_id() {
        return hd_id;
    }

    public void setHd_id(Integer hd_id) {
        this.hd_id = hd_id;
    }
}
