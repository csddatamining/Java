package com.study.springboot.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-27 23:00
 */
@Entity
public class Book {

    private int bid;
    private String bname;
    private Double bprice;
    private String pid;

    @Id
    @Column(name = "BID")
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "BNAME")
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @Basic
    @Column(name = "BPRICE")
    public Double getBprice() {
        return bprice;
    }

    public void setBprice(Double bprice) {
        this.bprice = bprice;
    }

    @Basic
    @Column(name = "PID")
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bid == book.bid &&
                Objects.equals(bname, book.bname) &&
                Objects.equals(bprice, book.bprice) &&
                Objects.equals(pid, book.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, bname, bprice, pid);
    }
}