package org.example.strategy;

public class DrinkInvigoratingStrategy implements Strategy {
    @Override
    public void haveADrink(String drink) {
        System.out.println("Выпит бодрящий напиток: " + drink);
    }
}
