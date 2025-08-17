package main.java.org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(

                new Student("Вася Пупкин").addBooks(List.of(
                        new Book("Фёдор Достоевский", "Преступление и наказание", 1866, 656),
                        new Book("Лев Толстой", "Война и мир", 1869, 1216),
                        new Book("Михаил Булгаков", "Мастер и Маргарита", 1967, 496),
                        new Book("Александр Пушкин", "Евгений Онегин", 1837, 384),
                        new Book("Антон Чехов", "Вишнёвый сад", 1904, 112))),

                new Student("Елена Малышева").addBooks(List.of(
                        new Book("Джордж Оруэлл", "1984", 1949, 320),
                        new Book("Эрнест Хемингуэй", "Старик и море", 1952, 128),
                        new Book("Франц Кафка", "Процесс", 1925, 368),
                        new Book("Стивен Кинг", "Оно", 1986, 1138),
                        new Book("Харуки Мураками", "Норвежский лес", 1987, 416))),

                new Student("Герольд Румянов").addBooks(List.of(
                        new Book("Борис Акунин", "Азазель", 1998, 384),
                        new Book("Дэн Браун", "Код да Винчи", 2003, 454),
                        new Book("Виктор Пелевин", "Generation", 1999, 384),
                        new Book("Конан Дойл", "Приключения Шерлока Холмса", 1892, 352),
                        new Book("Рэй Брэдбери", "451 градус по Фаренгейту", 1953, 240)))

        );

        students.stream()
                .peek(s -> System.out.println(s))
                .map(s -> s.getBooks())
                .flatMap(books1 -> books1.stream())
                .distinct()
                .filter(y -> y.yearRelease() > 2000)
                .sorted(Comparator.comparingInt(book1 -> book1.pages()))
                .limit(3)
                .map(book -> book.yearRelease())
                .findFirst()
                .ifPresentOrElse(year -> System.out.println("Год выпуска первой книги: " + year), () -> System.out.println("Не найдено подходящей книги"));

    }
}
