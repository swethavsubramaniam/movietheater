package com.jpmc.theater;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class
Theater {

    final LocalDateProvider provider;
    private final List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public JSONObject jsonifySchedule() {
        JSONObject jsonOuter = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        schedule.forEach(s -> {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("sequence", s.getSequenceOfTheDay());
                    jsonObject.put("startTime", s.getStartTime());
                    jsonObject.put("title", s.getMovie().getTitle());
                    jsonObject.put("runningTime", humanReadableFormat(s.getMovie().getRunningTime()));
                    jsonObject.put("movieFee", s.getMovieFee());
                    jsonArray.put(jsonObject);
                }
        );
        jsonOuter.put(provider.currentDate().toString(), jsonArray);
        return jsonOuter;
    }

    public String createScheduleString() {
        StringBuilder sb = new StringBuilder();
        sb.append(provider.currentDate() + "\n");
        sb.append("===================================================" + "\n");
        schedule.forEach(s ->
                sb.append(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee() + "\n")
        );
        sb.append("===================================================" + "\n");
        return sb.toString();
    }

    public String printSchedulAsText() {
        String str = createScheduleString();
        System.out.println(str);
        return str;
    }

    public String printScheduleAsJSON() {
        JSONObject jsonObject = jsonifySchedule();
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }


    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }

}
