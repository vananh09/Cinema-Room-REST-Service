package cinema;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CinemaServices {
    static Cinema cinema = new Cinema(9, 9);

    // This is Post service use for purchase ticket
    public static ResponseEntity<?> purchase(Seat seat) {
        if (seat.getColumn() > cinema.gettotal_columns()
                || seat.getRow() > cinema.gettotal_rows()
                || seat.getRow() < 1
                || seat.getColumn() < 1) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < cinema.getavailable_seats().size(); i++) {
            Seat s = cinema.getavailable_seats().get(i);
            if (s.equals(seat)) {
                Ticket ticket = new Ticket(UUID.randomUUID(), s);
                cinema.getTickets().add(ticket);
                cinema.getavailable_seats().remove(i);
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    // This is Post service use for return the ticket
    public static ResponseEntity<?> returnTicket(Token token) {
        List<Ticket> tickets = cinema.getTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getToken().equals(token.getToken())) {
                tickets.remove(ticket);
                cinema.getavailable_seats().add(ticket.getTicket());
                return new ResponseEntity<>(Map.of("returned_ticket", ticket.getTicket()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    // This is Post service use for return the information for admin of cinema

    public static ResponseEntity<?> stats(String password) {
        if(password != null && password.equals("super_secret")) {
            Map<String, Integer> statistic = new HashMap<>();
            int current_income = 0;
            List<Ticket> tickets = cinema.getTickets();
            for(Ticket ticket : tickets) {
                current_income += ticket.getTicket().getPrice();
            }
            int number_of_available_seats = cinema.getavailable_seats().size();
            int number_of_purchased_tickets = cinema.getTickets().size();
            statistic.put("current_income", current_income);
            statistic.put("number_of_available_seats", number_of_available_seats);
            statistic.put("number_of_purchased_tickets", number_of_purchased_tickets);
            return new ResponseEntity<>(statistic, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
