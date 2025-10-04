package org.homeWork;

public class Main {
    public static void main(String[] args) {
        TestHashMap<String, String> myMap = new TestHashMap<>();
        myMap.put("Хилка", "Зелье лечения");
        myMap.put("Мана", "Зелье маны");
        System.out.println(myMap.get("Хилка"));
        System.out.println(myMap.get("Мана"));
        myMap.remove("Хилка");
        System.out.println(myMap.get("Хилка"));
        System.out.println("Размер: " + myMap.size());
        myMap.put("Мана", "Зелье маны");
        System.out.println("Размер: " + myMap.size());
    }
}
