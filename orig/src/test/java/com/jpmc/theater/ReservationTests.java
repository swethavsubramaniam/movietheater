package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

// new tests
public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        LocalDate date = LocalDate.of(2022, 11, 25);
        // test for sequence discount
        var showing1 = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 2),
                1,
                LocalDateTime.of(date, LocalTime.of(17, 0))
        );
        assertEquals(28.5, new Reservation(customer, showing1, 3).totalFee());

        // test for showtime start discount
        var showing2 = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(date, LocalTime.of(12, 0))
        );
        assertEquals(28.125, new Reservation(customer, showing2, 3).totalFee());

        // test for seventh day of the month discount
        date = LocalDate.of(2022, 11, 7);
        var showing3 = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 3),
                4,
                LocalDateTime.of(date, LocalTime.of(18, 0))
        );
        assertEquals(34.5, new Reservation(customer, showing3, 3).totalFee());

        // test for sequence discounts
        var showing4 = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 2),
                1,
                LocalDateTime.of(date, LocalTime.of(17, 0))
        );
        assertEquals(28.5, new Reservation(customer, showing4, 3).totalFee());
        showing4 = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 2),
                2,
                LocalDateTime.of(date, LocalTime.of(17, 0))
        );
        assertEquals(31.5, new Reservation(customer, showing4, 3).totalFee());


        // test to show that max discount is applied when sequence, seventhDay, special discount & start time discounts are applicable
        var showing5 = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(date, LocalTime.of(12, 0))
        );
        assertEquals(28.125, new Reservation(customer, showing5, 3).totalFee());
    }
}
