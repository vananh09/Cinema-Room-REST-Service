package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private final int total_rows;
    private final int total_columns;
    private List<Seat> available_seats;

    @JsonIgnore
    private List<Ticket> tickets;

    @JsonIgnore
    private int currentIncome;

    public Cinema(int total_rows, int totalColumns) {
        this.total_rows = total_rows;
        this.total_columns = totalColumns;
        this.available_seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                Seat seat = new Seat(i, j);
                available_seats.add(seat);
            }
        }
        this.tickets = new ArrayList<>();
    }

    public int gettotal_rows() {
        return total_rows;
    }

    public int gettotal_columns() {
        return total_columns;
    }

    public List<Seat> getavailable_seats() {
        return available_seats;
    }

    public void setavailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    // Additional Function
    public int getCurrentIncome(List<Ticket> tickets) {
        for(Ticket ticket : tickets) {
            this.currentIncome += ticket.getTicket().getPrice();
        }
        return this.currentIncome;
    }

    public boolean isSeatOutOfRange(Seat seat) {
        if (seat.getColumn() < 1 || seat.getColumn() > total_columns) {
            return true;
        }
        return seat.getRow() < 1 || seat.getRow() > total_rows;
    }
}
