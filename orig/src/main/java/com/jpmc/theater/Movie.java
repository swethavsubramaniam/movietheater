package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static final int MOVIE_CODE_SPECIAL = 1;

    private final String title;
    private final Duration runningTime;
    private final double ticketPrice;
    private final int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return getTicketPrice() - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
    }

    private double getDiscount(int showSequence, LocalDateTime startTime) {
        double specialDiscount = 0;
        double sequenceDiscount = 0;
        double showingStartTimeDiscount = 0;
        double seventhDayOfMonthDiscount = 0;

        LocalTime showTimeStartDiscount = LocalTime.of(11, 00, 00);
        LocalTime showTimeEndDiscount = LocalTime.of(16, 00, 00);

        if (startTime.toLocalTime().isAfter(showTimeStartDiscount) && startTime.toLocalTime().isBefore(showTimeEndDiscount)) {
            showingStartTimeDiscount = getTicketPrice() * 0.25; // 25% discount for movies that start between 11 AM - 4 PM
        }

        if (startTime.getDayOfMonth() == 7) {
            seventhDayOfMonthDiscount = 1; // $1 discount for movies that are shown on the  7th
        }

        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = getTicketPrice() * 0.2;  // 20% discount for special movie
        }

        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {

            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        // biggest discount of the four discounts win
        return Math.max(Math.max(Math.max(showingStartTimeDiscount, sequenceDiscount), specialDiscount), seventhDayOfMonthDiscount);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.getTicketPrice(), getTicketPrice()) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }

    @Override
    public String toString() {
        return "name: " + title;
    }

}