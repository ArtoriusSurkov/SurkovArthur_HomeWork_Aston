package org.example;

import java.io.IOException;

public class MyFileException extends IOException {

    public MyFileException(String message) {
        super(message);
    }

    public MyFileException(Exception cause) {
        super(cause);
    }

    public MyFileException(String message, Exception cause){
        super(message,cause);
    }

}
