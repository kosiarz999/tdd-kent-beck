package com.kos.tddkentbeck;

public interface Expression {
    Money reduce(Bank bank, String to);
}
