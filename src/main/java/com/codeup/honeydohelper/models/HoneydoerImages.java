package com.codeup.honeydohelper.models;

import jakarta.persistence.*;


@Entity
@Table(name = "honeydoer_images")
public class HoneydoerImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "file_path", nullable = false, length = 100)
    private String file_path;

    @Column(name = "hd_id", nullable = false)
    private Integer hd_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getHd_id() {
        return hd_id;
    }

    public void setHd_id(Integer hd_id) {
        this.hd_id = hd_id;
    }
}
