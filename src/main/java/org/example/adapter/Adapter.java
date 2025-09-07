package org.example.adapter;

public class Adapter implements Target {

    private final AdapterVersionOne adapterVersionOne;

    public Adapter(AdapterVersionOne adapterVersionOne) {
        this.adapterVersionOne = adapterVersionOne;
    }

    @Override
    public String sayVersion() {
        return ("Адаптер: " + this.adapterVersionOne.getVersion());
    }
}
