package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "description", nullable = false, length = 125)
    private String description;

    @Column(name = "img_file_path", nullable = false, length = 125)
    private String img_file_path;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Services> services;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public Categories() {}

    public Categories(String name, String description, String img_file_path) {
        this.name = name;
        this.description = description;
        this.img_file_path = img_file_path;
    }

    public Categories(Integer id, String name, String description, String img_file_path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_file_path = img_file_path;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getImg_file_path() {return img_file_path;}
    public void setImg_file_path(String img_file_path) {this.img_file_path = img_file_path;}
}
