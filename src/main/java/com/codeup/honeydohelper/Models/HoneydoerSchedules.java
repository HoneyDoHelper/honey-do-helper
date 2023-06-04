package com.codeup.honeydohelper.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "honeydoer_schedules")

public class HoneydoerSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week", nullable = false)
    private LocalDate dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "time_block_id", nullable = false)
    private TimeBlocks timeBlock;

    @ManyToOne
    @JoinColumn(name = "honeydoer_id", nullable = false)
    private Honeydoers honeydoer;


    /*////////////////////////////////////////////////////////////////
    CONSTRUCTORS
    ////////////////////////////////////////////////////////////////*/
    public HoneydoerSchedules() {}

    public HoneydoerSchedules(LocalDate dayOfWeek, TimeBlocks timeBlock, Honeydoers honeydoer) {
        this.dayOfWeek = dayOfWeek;
        this.timeBlock = timeBlock;
        this.honeydoer = honeydoer;
    }

    public HoneydoerSchedules(int id, LocalDate dayOfWeek, TimeBlocks timeBlock, Honeydoers honeydoer) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.timeBlock = timeBlock;
        this.honeydoer = honeydoer;
    }


    /*////////////////////////////////////////////////////////////////
    GETTERS & SETTERS
    ////////////////////////////////////////////////////////////////*/
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public LocalDate getDayOfWeek() {return dayOfWeek;}
    public void setDayOfWeek(LocalDate dayOfWeek) {this.dayOfWeek = dayOfWeek;}

    public TimeBlocks getTimeBlock() {return timeBlock;}
    public void setTimeBlock(TimeBlocks timeBlock) {this.timeBlock = timeBlock;}

    public Honeydoers getHoneydoer() {return honeydoer;}
    public void setHoneydoer(Honeydoers honeydoer) {this.honeydoer = honeydoer;}
}
