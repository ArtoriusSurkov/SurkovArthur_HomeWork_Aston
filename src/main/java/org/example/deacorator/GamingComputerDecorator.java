package org.example.deacorator;

public class GamingComputerDecorator extends ComputerDecorator {
    public GamingComputerDecorator(Computer computer) {
        super(computer);
    }

    @Override
    public void sayInfo() {
        super.sayInfo();
        setType();
    }
    private void setType(){
        System.out.println("Тип: Геймерский");
    }

}
