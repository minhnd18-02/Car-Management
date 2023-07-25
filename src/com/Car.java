/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author ADMIN
 */
public class Car implements Comparable<Car> {

    private String carID;
    private Brand brand;
    private String color, frameID, engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarId() {
        return carID;
    }

    public void setCarId(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return "<" + carID + ", " + brand.getBrandId() + ", " + color + ", " + frameID + ", " + engineID + ">";
    }

    public String screenString() {
        return "<" + brand.toString() + ",\n" + carID + ", " + color + ", " + frameID + ", " + engineID + ">";
    }

    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0) {
            return d;
        }
        return this.carID.compareTo(c.carID);

    }

}
