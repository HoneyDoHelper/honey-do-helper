package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;


@Entity
@Table(name = "honeydoer_images")
public class HoneydoerImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "file_path", nullable = false, length = 100)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "honeydoer_id", nullable = false)
    private Honeydoers honeydoer;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public HoneydoerImages() {}

    public HoneydoerImages(String filePath, Honeydoers honeydoer) {
        this.filePath = filePath;
        this.honeydoer = honeydoer;
    }

    public HoneydoerImages(int id, String filePath, Honeydoers honeydoer) {
        this.id = id;
        this.filePath = filePath;
        this.honeydoer = honeydoer;
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

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Honeydoers getHoneydoer() {
        return honeydoer;
    }
    public void setHoneydoer(Honeydoers honeydoer) {
        this.honeydoer = honeydoer;
    }
}
