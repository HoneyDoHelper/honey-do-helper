package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer_services")
public class HoneydoerServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "rate", nullable = false)
    private float rate;

    @Column(name = "about_service", nullable = false)
    private String aboutService;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Services services;

    @ManyToOne
    @JoinColumn(name = "honeydoer_id", nullable = false)
    private Honeydoers honeydoers;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public HoneydoerServices() {}

    public HoneydoerServices(float rate, String aboutService, Services services, Honeydoers honeydoers) {
        this.rate = rate;
        this.aboutService = aboutService;
        this.services = services;
        this.honeydoers = honeydoers;
    }

    public HoneydoerServices(int id, float rate, String aboutService, Services services, Honeydoers honeydoers) {
        this.id = id;
        this.rate = rate;
        this.aboutService = aboutService;
        this.services = services;
        this.honeydoers = honeydoers;
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

    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getAboutService() {
        return aboutService;
    }
    public void setAboutService(String aboutService) {
        this.aboutService = aboutService;
    }

    public Services getServices() {
        return services;
    }
    public void setServices(Services services) {
        this.services = services;
    }

    public Honeydoers getHoneydoers() {
        return honeydoers;
    }
    public void setHoneydoers(Honeydoers honeydoers) {
        this.honeydoers = honeydoers;
    }
}
