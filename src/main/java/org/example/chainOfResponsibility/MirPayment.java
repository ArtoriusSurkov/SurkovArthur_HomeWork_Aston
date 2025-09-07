package org.example.chainOfResponsibility;

public class MirPayment implements Payment{
    private Payment nextPayment;

    @Override
    public void setNext(Payment payment) {
        this.nextPayment = payment;
    }

    @Override
    public void pay() {
        System.out.println("Оплата при помощи системы платежей - МИР");
        if (nextPayment != null){
            nextPayment.pay();
        }
    }
}
