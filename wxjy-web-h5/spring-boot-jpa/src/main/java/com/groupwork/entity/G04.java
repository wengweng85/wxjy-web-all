package com.groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by wengsh on 2019/4/3.
 */
@Entity
@Table(name="G04")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class G04 {
    @Id
    @Column(name = "g0400", nullable = false)
    @GeneratedValue(generator = "jpa-uuid")
    private String g0400;

    @Column
    private String g0300;

    @Column
    private String g0401;

    @Column
    private String g0402;

    @Column
    private String g0403;
    @Column
    private String g0404;

    @Column
    private String g0405;
    @Column
    private String g0406;

    public String getG0300() {
        return g0300;
    }

    public void setG0300(String g0300) {
        this.g0300 = g0300;
    }

    public String getG0400() {
        return g0400;
    }

    public void setG0400(String g0400) {
        this.g0400 = g0400;
    }

    public String getG0401() {
        return g0401;
    }

    public void setG0401(String g0401) {
        this.g0401 = g0401;
    }

    public String getG0402() {
        return g0402;
    }

    public void setG0402(String g0402) {
        this.g0402 = g0402;
    }

    public String getG0403() {
        return g0403;
    }

    public void setG0403(String g0403) {
        this.g0403 = g0403;
    }

    public String getG0404() {
        return g0404;
    }

    public void setG0404(String g0404) {
        this.g0404 = g0404;
    }

    public String getG0405() {
        return g0405;
    }

    public void setG0405(String g0405) {
        this.g0405 = g0405;
    }

    public String getG0406() {
        return g0406;
    }

    public void setG0406(String g0406) {
        this.g0406 = g0406;
    }
}
