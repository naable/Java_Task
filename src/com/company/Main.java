package com.company;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.company.Card;
import com.company.Product;

public class Main {

    static List<Product> productsList = new ArrayList<>();
    static List<Card> cardsList = new ArrayList<>();

    static public float total;
    static public float discount;


    static Date date = new Date();
    static SimpleDateFormat curDate = new SimpleDateFormat("dd-MM-yyyy");
    static SimpleDateFormat curTime = new SimpleDateFormat("HH:mm:ss");




    public static void main(String[] args) throws IOException {


        initData();
        discount = getCardDiscount(getInputCardNumber(args));
        checkCount(getInputProducts(args));

    }

    public static int[] getInputProducts(String[] args) throws FileNotFoundException {
        Scanner fileName = new Scanner(new FileInputStream(args[0]));
        String[] divide = fileName.nextLine().split(" ");
        int [] outputProds = new int[divide.length];
        for(int i = 0; i < divide.length; i++) {
            if(!divide[i].contains("card")) {
                outputProds[i] = Integer.parseInt(divide[i].replaceAll("-", ""));
            }
        }
        return outputProds;
    }


    public static int getInputCardNumber(String[] args) throws FileNotFoundException {
        Scanner fileName = new Scanner(new FileInputStream(args[0]));
        String[] divide = fileName.nextLine().split(" ");
        int cardNumber = 0;
        for (int i = 0; i < divide.length; i++) {
            if (divide[i].contains("card-")) {
                cardNumber = Integer.parseInt(divide[i].replaceAll("card-", ""));
            }
        }
        return cardNumber;
    }

    public static float getCardDiscount(int cardNumber) {
        float discount = 0;
        if(cardNumber > 0) {
            for(Card card: cardsList) {
                if(cardNumber == card.getCardNumber()) {
                    discount = card.getDiscount() / 100;
                }
            }
        }
        return discount;
    }

    public static void checkCount(int[] products) throws IOException {
        float currentProdSum;
        File file = new File("Example.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        System.out.println("\t\t\tCASH RECEIPT\t\t\t");
        writer.write("\t\t\tCASH RECEIPT\t\t\t\n");
        System.out.println("\t\t\tSUPERMARKET 123\t\t\t");
        writer.write("\t\t\tSUPERMARKET 123\t\t\t\n");
        System.out.println("\t\t\tVILLAGE KUKUEVO, 1\t\t\t");
        writer.write("\t\t\tVILLAGE KUKUEVO, 1\t\t\t\n");
        System.out.println("\t\t\tTel : +123-456-7890\t\t\t");
        writer.write("\t\t\tTel : +123-456-7890\t\t\t\n");
        System.out.println("Cashier" + "\t№" + (int)(100 * Math.random()) + "\t\t\t\t\t" + "Date: " + curDate.format(date));
        writer.write("Cashier" + "\t№" + (int)(100 * Math.random()) + "\t\t\t\t\t" + "Date: " + curDate.format(date) + "\n");
        System.out.println("\t\t\t\t\t\t\tTime: " + curTime.format(date));
        writer.write("\t\t\t\t\t\tTime: " + curTime.format(date) + "\n");
        System.out.println("___________________________________________");
        writer.write("___________________________________________\n");
        System.out.println("Amount\tDescription\t\t\tPrice\tTotal");
        writer.write("Amount\tDescription\t\t\tPrice\tTotal\n");
        for(int i = 0; i < products.length; i++){
            for(Product product: productsList){
                if(product.getId() == products[i]/10){
                    currentProdSum = (product.getPrice() * (products[i]%10));
                    System.out.print(products[i]%10 + "\t\t" + product.getName() + "\t\t\t\t" + product.getPrice() + "\t");
                    writer.write("\n" + products[i]%10 + "\t\t" + product.getName() + "\t\t\t" + product.getPrice() + "\t");
                    if(product.getDiscount() && (products[i]%10)>5) {
                        currentProdSum = currentProdSum - (currentProdSum * 0.1f);
                    }
                    System.out.println(currentProdSum);
                    writer.write( currentProdSum + "\n");
                    total += currentProdSum;
                }
            }
        }
        System.out.println("___________________________________________");
        writer.write( "___________________________________________\n");
        System.out.println("Total without discount\t\t\t\t" + total);
        writer.write( "Total without discount\t\t\t\t" + total + "\n");
        System.out.println("Discount\t\t\t\t\t\t\t" + Math.round(discount*100) + "%");
        writer.write( "Discount\t\t\t\t\t" + Math.round(discount*100) + "%\n");
        total = total - total*discount;
        System.out.println("Total\t\t\t\t\t\t\t\t" + total );
        writer.write( "Total\t\t\t\t\t\t" + total + "\n");
        writer.flush();
        writer.close();
    }

    public static void initData() {

        productsList.add(new Product(1, "Bread", 1.45f, true));
        productsList.add(new Product(2, "Milk", 3.19f, true));
        productsList.add(new Product(3, "Beef", 10.23f, false));
        productsList.add(new Product(4, "Onion", 4.12f, true));
        productsList.add(new Product(5, "Carrot", 6.81f, false));
        productsList.add(new Product(6, "Apple", 5.4f, false));
        productsList.add(new Product(7, "Lemon", 7.2f, true));
        productsList.add(new Product(8, "Cheese", 9.9f, false));
        productsList.add(new Product(9, "Candy bar", 1.12f, true));
        productsList.add(new Product(10, "Honey", 13.5f, false));

        cardsList.add(new Card(1238, 20));
        cardsList.add(new Card(1864, 15));
        cardsList.add(new Card(1234, 15));
        cardsList.add(new Card(1222, 5));
        cardsList.add(new Card(9531, 10));

    }
}

