package com.kos.tddkentbeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        Money six = Money.franc(6);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
        assertEquals(Money.franc(12), six.times(2));
        assertEquals(Money.franc(18), six.times(3));
    }

    @Test
    void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertNotEquals(Money.dollar(5), Money.franc(5));
    }

    @Test
    void testMultiplicationFranc() {

    }

    @Test
    void testEqualityFranc() {
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(6));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }
}
