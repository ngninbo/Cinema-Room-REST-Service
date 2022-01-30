package cinema.service;

import cinema.model.*;
import cinema.util.CinemaUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static cinema.util.CinemaData.TOTAL_COLUMNS;
import static cinema.util.CinemaData.TOTAL_ROWS;

@Service
public class CinemaServiceImpl implements CinemaService {

    int currentIncome;
    List<Ticket> ticketList;
    List<Seat> seatList;
    ConcurrentHashMap<String, Purchase> purchasedTicketMap = new ConcurrentHashMap<>();

    {
        ticketList = CinemaUtil.getTicketList();
        seatList = CinemaUtil.generateSeats();
        currentIncome = 0;
    }

    @Override
    public Purchase purchase(BookedSeat bookedSeat) {
        Purchase ticket = null;
        for (int i = 0; i < seatList.size(); i++) {
            Seat seat = seatList.get(i);
            if (!CinemaUtil.isBooked(bookedSeat, seat)) {
                seat.setBooked(true);
                seatList.set(i, seat);
                for (int j = 0; j < ticketList.size(); j++) {
                    Ticket tmp = ticketList.get(j);
                    if (tmp.getColumn() == bookedSeat.getColumn() && tmp.getRow() == bookedSeat.getRow()) {
                        String token = UUID.randomUUID().toString();
                        ticket = new Purchase(token, tmp);
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

    @Override
    public Cinema getCinemaInfo() {
        return new Cinema(TOTAL_ROWS, TOTAL_COLUMNS, ticketList);
    }

    @Override
    public Map<String, Ticket> refund(Map<String, String> token) {
        Map<String, Ticket> returnTicket = null;
        String key = token.get("token");
        if (purchasedTicketMap.containsKey(key)) {
            Purchase purchase = purchasedTicketMap.get(key);
            Ticket ticket = purchase.getTicket();
            Seat seat = new Seat(ticket.getRow(), ticket.getColumn(), false);
            seatList.add(seat);
            returnTicket = Collections.singletonMap("returned_ticket", ticket);
            ticketList.add(ticket);
            ticketList.sort((t1, t2) -> t1.equals(t2) ? 0 : -1);

            purchasedTicketMap.remove(key, purchase);
            currentIncome -= ticket.getPrice();
        }

        return returnTicket;
    }

    @Override
    public Map<String, Integer> getStats() {

        return Map.of("current_income", currentIncome,
                "number_of_available_seats", ticketList.size(),
                "number_of_purchased_tickets", purchasedTicketMap.size());
    }


}
