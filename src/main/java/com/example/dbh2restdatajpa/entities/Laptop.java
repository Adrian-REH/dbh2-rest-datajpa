package com.example.dbh2restdatajpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private Integer ram;
    private Boolean isMacOs;

    public Laptop(){

    }
    public Laptop(Long id, String marca, Integer ram, Boolean isMacOs) {
        this.id = id;
        this.marca = marca;
        this.ram = ram;
        this.isMacOs = isMacOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Boolean getMacOs() {
        return isMacOs;
    }

    public void setMacOs(Boolean macOs) {
        isMacOs = macOs;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", ram=" + ram +
                ", isMacOs=" + isMacOs +
                '}';
    }
}
