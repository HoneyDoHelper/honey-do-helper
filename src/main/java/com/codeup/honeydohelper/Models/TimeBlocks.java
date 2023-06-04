package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class TimeBlocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start", nullable = false)
    private LocalTime start;

    @Column(name = "end", nullable = false)
    private LocalTime end;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public TimeBlocks() {}

    public TimeBlocks(String name, LocalTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public TimeBlocks(int id, String name, LocalTime start, LocalTime end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public LocalTime getStart() {return start;}
    public void setStart(LocalTime start) {this.start = start;}

    public LocalTime getEnd() {return end;}
    public void setEnd(LocalTime end) {this.end = end;}
}

