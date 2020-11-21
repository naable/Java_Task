package com.company;

//Модель скидочной карты

public class Card{

    private int cardNumber; //Номер скидочной карты
    private float discount; //Размер скидки на карте

    public Card(int cardNumber, float discount) {
        this.cardNumber = cardNumber;
        this.discount = discount;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public float getDiscount() {
        return discount;
    }
}
