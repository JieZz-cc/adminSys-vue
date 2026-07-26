package com.itheima.ToObject;

public class Car {
    private String brand;
    private int price;
    private String type;

    public Car() {}
    public Car(String brand, int price, String type) {
        this.brand = brand;
        this.price = price;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
