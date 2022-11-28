package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.of(2022, 11, 25), LocalTime.of(18, 0)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testMovieInfo() {
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie spiderMan2 = new Movie("Spider-Man: Away From Home", Duration.ofMinutes(900), 12.5, 1);
        assertTrue(spiderMan1.equals(spiderMan1));
        assertFalse(spiderMan2.equals(spiderMan1));
        assertFalse(spiderMan1.equals(null));
        assertFalse(spiderMan1.hashCode() == spiderMan2.hashCode());
        assertEquals("name: Spider-Man: Away From Home", spiderMan2.toString());
    }
}
