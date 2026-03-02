
package com.example.spring1.api.dto;

import java.util.ArrayList;
import java.util.List;

public class DealershipDTO {
    private Long dealershipId;
    private String name;
    private String location;

    private String manufacturerName;

    private List<CarDTO> cars = new ArrayList<>();

    public Long getDealershipId() {
        return dealershipId;
    }
    public void setDealershipId(Long dealershipId) {
        this.dealershipId = dealershipId;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<CarDTO> getCars() {return cars;}
    public void setCars(List<CarDTO> cars) {this.cars = cars;}
}
