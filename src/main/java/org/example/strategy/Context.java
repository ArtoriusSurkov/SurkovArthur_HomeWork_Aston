package org.example.strategy;

public class Context {
    private Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public void haveADrink(String drink){
        strategy.haveADrink(drink);
    }
}
