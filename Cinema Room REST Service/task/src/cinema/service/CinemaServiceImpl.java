package cinema.service;

import cinema.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CinemaServiceImpl implements CinemaService {

    int total_rows = 9;
    int total_columns = 9;
    int firstRowsPrice = 10;
    int lastRowPrice = 8;
    int currentIncome;

    List<Ticket> ticketList;
    List<Seat> seatList;
    ConcurrentHashMap<String, PurchasedTicket> purchasedTicketMap = new ConcurrentHashMap<>();

    {
        ticketList = getTicketList();
        seatList = generateSeats();
        currentIncome = 0;
    }

    @Override
    public PurchasedTicket purchase(Purchase purchase) {
        PurchasedTicket ticket = null;
        for (int i = 0; i < seatList.size(); i++) {
           Seat seat = seatList.get(i);
           if (!isBooked(purchase, seat)) {
               seat.setBooked(true);
               seatList.set(i, seat);
               for (int j = 0; j < ticketList.size(); j++) {
                   Ticket tmp = ticketList.get(j);
                   if (tmp.getColumn() == purchase.getColumn() && tmp.getRow() == purchase.getRow()) {
                       String token = UUID.randomUUID().toString();
                       ticket = new PurchasedTicket(token, tmp);
                       purchasedTicketMap.put(token, ticket);
                       ticketList.remove(j);
                       currentIncome += tmp.getPrice();
                       break;
                   }
               }
           }
       }
        return ticket;
    }

    private boolean isBooked(Purchase purchase, Seat seat) {
        return seat.getRow() == purchase.getRow() && seat.getColumn() == purchase.getColumn() && !seat.isBooked();
    }

    @Override
    public Cinema getCinemaInfo() {
        return new Cinema(total_rows, total_columns, ticketList);
    }

    @Override
    public boolean validate(Purchase purchase) {
        return purchase.getRow() > 0 && purchase.getRow() <= total_rows &&
                purchase.getColumn() > 0 && purchase.getColumn() <= total_columns;
    }

    @Override
    public Map<String, Ticket> refund(Map<String, String> token) {
        Map<String, Ticket> returnTicket = null;
        String key = token.get("token");
        if (purchasedTicketMap.containsKey(key)) {
            PurchasedTicket purchasedTicket = purchasedTicketMap.get(key);
            Ticket ticket = purchasedTicket.getTicket();
            Seat seat = new Seat(ticket.getRow(), ticket.getColumn(), false);
            seatList.add(seat);
            returnTicket = new HashMap<>();
            returnTicket.put("returned_ticket", ticket);
            ticketList.add(ticket);
            ticketList.sort((t1, t2) -> {
                if (t1.equals(t2)) {
                    return 0;
                } else {
                    return -1;
                }
            });

            purchasedTicketMap.remove(key, purchasedTicket);
            currentIncome -= ticket.getPrice();
        }

        return returnTicket;
    }

    @Override
    public Stats getStats() {
        return new Stats(currentIncome, ticketList.size(), purchasedTicketMap.size());
    }

    List<Ticket> getTicketList() {

        List<Ticket> seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                Ticket seat = i <= 4 ? new Ticket(i, j, firstRowsPrice) : new Ticket(i, j, lastRowPrice);
                seats.add(seat);
            }
        }

        return seats;
    }

    List<Seat> generateSeats() {

        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(i, j);
                seats.add(seat);
            }
        }

        return seats;
    }
}
