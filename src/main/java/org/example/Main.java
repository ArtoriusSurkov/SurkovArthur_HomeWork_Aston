package org.example;

import org.example.adapter.AdapterVersionOne;
import org.example.adapter.Adapter;
import org.example.adapter.Target;
import org.example.builder.Person;
import org.example.chainOfResponsibility.MirPayment;
import org.example.chainOfResponsibility.MyPayment;
import org.example.chainOfResponsibility.Payment;
import org.example.deacorator.Computer;
import org.example.deacorator.GamingComputerDecorator;
import org.example.deacorator.Notebook;
import org.example.deacorator.PersonalComputer;
import org.example.proxy.ProxyTaskFile;
import org.example.proxy.TaskFile;
import org.example.strategy.Context;
import org.example.strategy.DrinkInvigoratingStrategy;
import org.example.strategy.DrinkSweetStrategy;


public class Main {
    public static void main(String[] args) {
        System.out.println("1.Адаптер");
        AdapterVersionOne adapterVersionOne = new AdapterVersionOne();
        Target adapter = new Adapter(adapterVersionOne);
        System.out.println(adapter.sayVersion());

        System.out.println("\n2.Билдер");
        Person person = new Person.PersonBuilder("Алексей","Емелин").setAge(14).setPlaceOfResidence("Россия").create();
        System.out.println(person);

        System.out.println("\n3.Цепочка обязанностей");
        Payment mirPayment = new MirPayment();
        Payment myPayment = new MyPayment();
        mirPayment.setNext(myPayment);
        mirPayment.pay();

        System.out.println("\n4.Декоратор");
        Computer personalComputer = new PersonalComputer();
        Computer gamingComputerDecorator = new GamingComputerDecorator(new Notebook());
        personalComputer.sayInfo();
        System.out.println();
        gamingComputerDecorator.sayInfo();

        System.out.println("\n5.Прокси");
        TaskFile taskFile = new ProxyTaskFile("taskFile.exe");
        taskFile.viewFile();
        taskFile.viewFile();

        System.out.println("\n6.Стратегия");
        Context context = new Context(new DrinkInvigoratingStrategy());
        context.haveADrink("Кофе");
        context = new Context(new DrinkSweetStrategy());
        context.haveADrink("Чай");
    }
}
