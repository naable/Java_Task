package com.company;

public class Product{

    private int id; //id товара
    private String name;  //Название товара
    private float price;  //Цена товара
    private boolean discount; //Акционный

    public Product(int id, String name, float price, boolean discount) { //конструктор товаров
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void  setPrice(float price){
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getDiscount() { return discount; }

    public void setDiscount(boolean discount) { this.discount = discount;  }

}
