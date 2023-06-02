package com.codeup.honeydohelper.models;

import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer")
public class Honeydoer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating", nullable = false)
}
