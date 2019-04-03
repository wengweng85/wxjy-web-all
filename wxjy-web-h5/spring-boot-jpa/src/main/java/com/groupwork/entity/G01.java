package com.groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by wengsh on 2019/4/3.
 */
@Entity
@Table(name="G01")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class G01 {
    @Id
    @Column(name="g0100",nullable = false)
    @GeneratedValue(generator = "jpa-uuid")
    private String g0100;

    @Column
    private String g0101;

    @Column
    private String g0102;

    @Column
    private String g0103;

    @Column
    private String g0104;

    @Column
    private String g0105;

    @Column
    private String g0106;

    @Column
    private String g0107;

    @Column
    private String g0108;

    @Column
    private String g0109;

    @Column
    private String g0110;


    public String getG0100() {
        return g0100;
    }

    public void setG0100(String g0100) {
        this.g0100 = g0100;
    }

    public String getG0101() {
        return g0101;
    }

    public void setG0101(String g0101) {
        this.g0101 = g0101;
    }

    public String getG0102() {
        return g0102;
    }

    public void setG0102(String g0102) {
        this.g0102 = g0102;
    }

    public String getG0103() {
        return g0103;
    }

    public void setG0103(String g0103) {
        this.g0103 = g0103;
    }

    public String getG0104() {
        return g0104;
    }

    public void setG0104(String g0104) {
        this.g0104 = g0104;
    }

    public String getG0105() {
        return g0105;
    }

    public void setG0105(String g0105) {
        this.g0105 = g0105;
    }

    public String getG0106() {
        return g0106;
    }

    public void setG0106(String g0106) {
        this.g0106 = g0106;
    }

    public String getG0107() {
        return g0107;
    }

    public void setG0107(String g0107) {
        this.g0107 = g0107;
    }

    public String getG0108() {
        return g0108;
    }

    public void setG0108(String g0108) {
        this.g0108 = g0108;
    }

    public String getG0109() {
        return g0109;
    }

    public void setG0109(String g0109) {
        this.g0109 = g0109;
    }

    public String getG0110() {
        return g0110;
    }

    public void setG0110(String g0110) {
        this.g0110 = g0110;
    }
}
