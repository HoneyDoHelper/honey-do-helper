package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false, length = 125)
    private String description;

    @Column(name = "img_file_path",nullable = false, length = 125)
    private String imgFilePath;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Services() {}

    public Services(String name, String description, String imgFilePath, Categories category) {
        this.name = name;
        this.description = description;
        this.imgFilePath = imgFilePath;
        this.category = category;
    }

    public Services(int id, String name, String description, String imgFilePath, Categories category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgFilePath = imgFilePath;
        this.category = category;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }
    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    public Categories getCategory() {
        return category;
    }
    public void setCategory(Categories category) {
        this.category = category;
    }
}
