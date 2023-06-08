package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "address", nullable = false, length = 75)
    private String address;

    @Column(name = "address2", length = 75)
    private String address2;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zip")
    private int zip;

    @Column(name = "phone", nullable = false)
    private long phone;

    @Column(name = "img_file_path", nullable = false, length = 125)
    private String imgFilePath;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private HoneyUsers user;

    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public UserProfiles() {}

    public UserProfiles(String address, String address2, String city, String state, int zip, long phone, String imgFilePath, HoneyUsers user) {
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.imgFilePath = imgFilePath;
        this.user = user;
    }

    public UserProfiles(int id, String address, String address2, String city, String state, int zip, long phone, String imgFilePath, HoneyUsers user) {
        this.id = id;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.imgFilePath = imgFilePath;
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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {return zip;}
    public void setZip(int zip) {
        this.zip = zip;
    }

    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }
    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    public HoneyUsers getUser() {
        return user;
    }
    public void setUser(HoneyUsers user) {
        this.user = user;
    }

}
