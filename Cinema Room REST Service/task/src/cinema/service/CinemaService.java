package cinema.service;

import cinema.model.*;

import java.util.Map;

public interface CinemaService {

    PurchasedTicket purchase(Purchase purchase);
    Cinema getCinemaInfo();
    boolean validate(Purchase purchase);
    Map<String, Ticket> refund(Map<String, String> token);
    Stats getStats();
}
