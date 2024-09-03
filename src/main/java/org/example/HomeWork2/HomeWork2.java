package org.example.HomeWork2;

public class HomeWork2<F, S> {
    private F first;
    private S second;

    public HomeWork2(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public <R> String sumCon() {
        if (this.first instanceof Number && this.second instanceof Number) {
            return String.valueOf(((Number) this.first).doubleValue() + ((Number) this.second).doubleValue());
        } else {
            return this.first + "" + this.second;
        }
    }

}