package com.kos.tddkentbeck;

public class Franc extends Money{

    private String currency;
    public Franc(int amount) {
        this.amount = amount;
        this.currency = "CHF";
    }

    @Override
    protected String currency() {
        return this.currency;
    }

    Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }
}
