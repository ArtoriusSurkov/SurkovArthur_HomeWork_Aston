package org.example.proxy;

public class ProxyTaskFile implements TaskFile {
    private final String myTaskFile;
    private RealTaskFile realTaskFile;
    public ProxyTaskFile(String taskFile) {
        this.myTaskFile = taskFile;
    }

    public void viewFile() {
        if(realTaskFile == null) {
            realTaskFile = new RealTaskFile(myTaskFile);
        }
        realTaskFile.viewFile();
    }
}
