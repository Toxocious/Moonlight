package net.swordie.ms.util;

import javax.persistence.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "systemtimes")
public class SystemTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "yr")
    private int year;
    @Column(name = "mnth")
    private int month;

    public SystemTime(){}

    public SystemTime(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
