package org.example.strategy;

public class DrinkSweetStrategy implements Strategy {
    @Override
    public void haveADrink(String drink) {
        System.out.println("Выпит сладкий напиток: " + drink);
    }
}
