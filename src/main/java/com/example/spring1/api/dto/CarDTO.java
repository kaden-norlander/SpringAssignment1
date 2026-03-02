package com.example.spring1.api.dto;

public class CarDTO {
    private Long vinid;
    private String make;
    private String color;
    private String model;

    private String displayname;

    public CarDTO () {}

    public Long getVinid() {return vinid;}
    public void setVinid(Long vinid) {this.vinid = vinid;}

    public String getMake() {return make;}
    public void setMake(String make) {this.make = make;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    public String getDisplayname() {return displayname;}
    public void setDisplayname(String displayname) {this.displayname = displayname;}
}