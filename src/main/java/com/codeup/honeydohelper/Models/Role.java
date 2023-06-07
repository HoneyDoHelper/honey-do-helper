package com.codeup.honeydohelper.Models;
import jakarta.persistence.*;
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 10)
    private String name;
}

