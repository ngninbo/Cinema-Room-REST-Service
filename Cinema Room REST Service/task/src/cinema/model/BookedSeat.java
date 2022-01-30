package cinema.model;

import java.util.Objects;

public class BookedSeat {

    private int row;
    private int column;

    public BookedSeat() {
    }

    public BookedSeat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookedSeat)) return false;
        BookedSeat bookedSeat = (BookedSeat) o;
        return getRow() == bookedSeat.getRow() && getColumn() == bookedSeat.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }
}
