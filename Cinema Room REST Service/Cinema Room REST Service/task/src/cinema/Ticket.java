package cinema;

import java.util.UUID;

public class Ticket {

    private Seat ticket;

    private UUID token;

    public Ticket(UUID token, Seat ticket) {
        this.ticket = ticket;
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
