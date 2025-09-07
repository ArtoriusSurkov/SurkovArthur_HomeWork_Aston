package org.example.proxy;

public class RealTaskFile implements TaskFile {
    private final String myTaskFile;

    public RealTaskFile(String taskFile){
        this.myTaskFile = taskFile;
        loadFile(taskFile);
    }

    public void loadFile(String task) {
        System.out.println("Загрузка файла: " + task);
    }

    public void viewFile() {
        System.out.println("Просмотр файла: " + myTaskFile);
    }
}
