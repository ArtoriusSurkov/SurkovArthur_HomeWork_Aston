package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileMaster {

    private final String name;

    private final File file;

    public FileMaster(String name) {

        this.name = name;
        file = new File(name);

        if (file.exists()) {
            System.out.println("Файл уже был создан ранее!");
        } else System.out.println("Файл успешно был создан!");

    }

    public void writeFile(String data) throws MyFileException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)){

            fileOutputStream.write((data).getBytes());

            System.out.println("Данные успешно внесены в файл!");

        } catch (IOException e) {

            throw new MyFileException("Ошибка записи!", e);

        }

    }


    public void readFile() throws MyFileException {

        System.out.println("Данные из файла:");

        try (FileInputStream fileInputStream = new FileInputStream(file)){

            int i;

            while ((i=fileInputStream.read()) != -1){

                System.out.print((char)i);

            }

        } catch (IOException e) {

            throw new MyFileException("Ошибка чтения!", e);

        }

    }


}
