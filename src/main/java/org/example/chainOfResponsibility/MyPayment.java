package org.example.chainOfResponsibility;

public class MyPayment implements Payment {
    private Payment nextPayment;

    @Override
    public void setNext(Payment payment) {
        this.nextPayment = payment;
    }

    @Override
    public void pay() {
        System.out.println("Оплата при помощи своей системы платежей");
        if (nextPayment != null){
            nextPayment.pay();
        }
    }
}
