package main.java.org.example;

public record Book(String author, String title, Integer yearRelease, Integer pages) {

    @Override
    public String toString() {
        return "{" +
                "Автор: '" + author + "', " +
                "Название: '" + title + "', " +
                "Год издания: " + yearRelease + ", " +
                "Кол-во страниц: " + pages +
                '}';
    }

}
