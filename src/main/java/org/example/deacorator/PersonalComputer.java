package org.example.deacorator;

public class PersonalComputer implements Computer{
    @Override
    public void sayInfo() {
        System.out.println("Персональный компьютер");
    }
}
