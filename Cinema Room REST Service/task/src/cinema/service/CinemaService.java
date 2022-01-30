package cinema.service;

import cinema.model.*;

import java.util.Map;

public interface CinemaService {

    Purchase purchase(BookedSeat bookedSeat);
    Cinema getCinemaInfo();
    Map<String, Ticket> refund(Map<String, String> token);
    Map<String, Integer> getStats();
}
