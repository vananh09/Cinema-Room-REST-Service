package cinema;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping("/seats")
    public Cinema getSeats() {
        return CinemaServices.cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        return CinemaServices.purchase(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {
        return CinemaServices.returnTicket(token);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats(@RequestParam(required = false) String password) {
        return CinemaServices.stats(password);
    }
}
