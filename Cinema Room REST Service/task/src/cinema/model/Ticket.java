package cinema.model;

import java.util.Objects;

public class Ticket extends Purchase {

    private int price;

    public Ticket(int row, int column, int price) {
        super(row, column);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        if (!super.equals(o)) return false;
        Ticket ticket = (Ticket) o;
        return getPrice() == ticket.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPrice());
    }
}
