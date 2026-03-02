package com.example.spring1.api.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dealerships")
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealershipId;

    private String name;
    private String location;

    // MANY Dealerships belong to ONE Manufacturer
    @ManyToOne
    @JoinColumn(name = "manufacturer_id") // This is the Foreign Key column in the DB
    private Manufacturer manufacturer;

    // MANY Dealerships have MANY Cars
    @ManyToMany
    @JoinTable(
            name = "dealership_cars", // The join table we created in pgAdmin
            joinColumns = @JoinColumn(name = "dealership_id"),
            inverseJoinColumns = @JoinColumn(name = "vinid")
    )
    private List<Car> cars = new ArrayList<>();

    public Long getDealershipId() {return dealershipId;}
    public void setDealershipId(Long dealershipId) {this.dealershipId = dealershipId;}

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public Manufacturer getManufacturer() {return manufacturer;}
    public void setManufacturer(Manufacturer manufacturer) {this.manufacturer = manufacturer;}

    public List<Car> getCars() {return cars;}
    public void setCars(List<Car> cars) {this.cars = cars;}

    // Helper method to easily add a car to inventory
    public void addCar(Car car) {
        this.cars.add(car);
        car.getDealerships().add(this);
    }
}
