package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
// new tests
public class CustomerTests {

    @Test
    void testCustomerInfo() {
        Customer customer1 = new Customer("John Doe", "123");
        Customer customer2 = new Customer("John Doe", "321");
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        assertTrue(customer1.equals(customer1));
        assertFalse(customer2.equals(customer1));
        assertFalse(customer2.equals(spiderMan1));
        assertFalse(customer2.hashCode() == customer1.hashCode());
        assertEquals("name: John Doe", customer1.toString());
    }
}
