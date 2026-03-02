package com.example.spring1.api.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vinid")
    private Long vinid;

    private String make;
    private String color;
    private String model;

    @ManyToMany(mappedBy = "cars")
    private List<Dealership> dealerships = new ArrayList<>();

    public Car() {}

    public Car (String make, String color, String model) {
        this.make = make;
        this.color = color;
        this.model = model;
    }

    public Long getId() {return vinid;}
    public void setId(Long id) {this.vinid = id;}

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    public List<Dealership> getDealerships() {return dealerships;}
    public void setDealerships(List<Dealership> dealerships) {this.dealerships = dealerships;}
}
