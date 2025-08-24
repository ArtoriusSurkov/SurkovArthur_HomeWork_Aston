package org.example;

public class Main {
    public static void main(String[] args) throws MyFileException {
        try {

            FileMaster fileMaster = new FileMaster("hello.txt");
            fileMaster.writeFile("12341234");
            fileMaster.readFile();

        } catch (MyFileException e) {

            throw new MyFileException(e);

        }


    }
}
