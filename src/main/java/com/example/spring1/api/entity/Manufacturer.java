package com.example.spring1.api.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerId;

    private String name;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    // ONE Manufacturer has MANY Dealerships
    // mappedBy = "manufacturer" tells Hibernate to look at the 'manufacturer' field in the Dealership class
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Dealership> dealerships = new ArrayList<>();


    public Long getManufacturerId() {return manufacturerId;}
    public void setManufacturerId(Long manufacturerId) {this.manufacturerId = manufacturerId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getCountryOfOrigin() {return countryOfOrigin;}
    public void setCountryOfOrigin(String countryOfOrigin) {this.countryOfOrigin = countryOfOrigin;}

    public List<Dealership> getDealerships() {return dealerships;}
    public void setDealerships(List<Dealership> dealerships) {this.dealerships = dealerships;}

    public void addDealership(Dealership dealership) {
        dealerships.add(dealership);
        dealership.setManufacturer(this);
    }
}