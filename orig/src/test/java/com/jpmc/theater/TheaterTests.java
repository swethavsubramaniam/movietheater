package com.jpmc.theater;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheaterTests {
    static Theater theater;
    static Customer john;

    @BeforeAll
    static void setUp() throws Exception {
        theater = new Theater(LocalDateProvider.singleton());
        john = new Customer("John Doe", "id-12345");
    }


    @Test
    void totalFeeForCustomer() {
        try {
            Reservation reservation = theater.reserve(john, 2, 4);
            assertEquals(40, reservation.totalFee());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void testTheaterReserve() {

        assertThrows(IllegalStateException.class,
                () -> {
                    theater.reserve(john, 25, 4);
                });
    }

    @Test
    void printMovieScheduleAsText() {
        assertNotNull(theater.printSchedulAsText());
    }

    @Test
    void printMovieScheduleAsJSON() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        assertNotNull(theater.printScheduleAsJSON());
    }

    @Test
    void jsonifyMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        JSONObject jsonObject = theater.jsonifySchedule();
        // check if th json object was created properly
        assertNotNull(jsonObject);
        // check the array size returned by the json object
        JSONArray jsonArray = jsonObject.getJSONArray(LocalDateProvider.singleton().currentDate().toString());
        assertEquals(9, jsonArray.toList().size());
    }
}
