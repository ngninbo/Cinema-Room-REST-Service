package cinema.util;

import cinema.model.BookedSeat;
import cinema.model.Seat;
import cinema.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static cinema.util.CinemaData.*;

public abstract class CinemaUtil {

    public static List<Ticket> getTicketList() {

        List<Ticket> seats = new ArrayList<>();
        for (int i = 1; i <= TOTAL_ROWS; i++) {
            for (int j = 1; j <= TOTAL_COLUMNS; j++) {
                Ticket seat = i <= FIRST_ROWS_LIMIT ? new Ticket(i, j, FIRST_ROWS_PRICE) : new Ticket(i, j, LAST_ROW_PRICE);
                seats.add(seat);
            }
        }

        return seats;
    }

    public static List<Seat> generateSeats() {

        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= TOTAL_ROWS; i++) {
            for (int j = 1; j <= TOTAL_COLUMNS; j++) {
                Seat seat = new Seat(i, j);
                seats.add(seat);
            }
        }

        return seats;
    }

    public static boolean isBooked(BookedSeat bookedSeat, Seat seat) {
        return seat.getRow() == bookedSeat.getRow() && seat.getColumn() == bookedSeat.getColumn() && !seat.isBooked();
    }

    public static boolean validate(BookedSeat bookedSeat) {
        return bookedSeat.getRow() > 0 && bookedSeat.getRow() <= TOTAL_ROWS &&
                bookedSeat.getColumn() > 0 && bookedSeat.getColumn() <= TOTAL_COLUMNS;
    }
}
