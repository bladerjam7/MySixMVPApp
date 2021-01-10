package com.americanairlines.mysixmvpapp.model;

public class Shoe {
    private int shoeSize;
    private String shoeModel;
    private int shoeID;
    private double shoePrice;
    private String imageID;

    public Shoe(int shoeSize, String shoeModel, int shoeID, double shoePrice, String imageID) {
        this.shoeSize = shoeSize;
        this.shoeModel = shoeModel;
        this.shoeID = shoeID;
        this.shoePrice = shoePrice;
        this.imageID = imageID;
    }

    public Shoe(int shoeSize, String shoeModel, double String, String imageID) {
        this.shoeSize = shoeSize;
        this.shoeModel = shoeModel;
        this.shoePrice = shoePrice;
        this.imageID = imageID;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public void setShoeModel(String shoeModel) {
        this.shoeModel = shoeModel;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public String getShoeModel() {
        return shoeModel;
    }

    public int getShoeID() {
        return shoeID;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public String getImageID() {
        return imageID;
    }
}
