package com.example.spring1.api.models;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long vin;

    private String make;
    private String color;

    public Car() {}

    public Car (String make, String color) {
        this.make = make;
        this.color = color;
    }

    public Long getVin() {
        return vin;
    }
    public void setVin(Long vin) {
        this.vin = vin;
    }

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
}
