package cinema.model;

public class Seat extends BookedSeat {

    private boolean isBooked;

    public Seat(int row, int column) {
        super(row, column);
        this.isBooked = false;
    }

    public Seat(int row, int column, boolean isBooked) {
        super(row, column);
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
