package com.codeup.honeydohelper.models;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 25)
    private String firstname;
    @Column(nullable = false, length = 25)
    private String lastname;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 25)
    private String email;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;
}
