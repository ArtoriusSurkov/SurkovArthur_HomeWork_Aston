package main.java.org.example;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String name;

    private final List<Book> books = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Student addBooks(List<Book> books) {
        this.books.addAll(books);
        return this;
    }

    @Override
    public String toString() {
        return "Студент: " +
                name +
                "\nСписок книг: " +
                books +
                "\n";
    }

}


