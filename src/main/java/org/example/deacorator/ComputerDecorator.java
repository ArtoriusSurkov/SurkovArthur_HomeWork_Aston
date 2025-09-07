package org.example.deacorator;

public abstract class ComputerDecorator implements Computer {
    protected Computer myComputer;
    public ComputerDecorator(Computer computer){
        this.myComputer = computer;
    }
    @Override
    public void sayInfo() {
        myComputer.sayInfo();
    }
}
