package org.example.chainOfResponsibility;

public interface Payment {
    void setNext(Payment payment);
    void pay();
}
