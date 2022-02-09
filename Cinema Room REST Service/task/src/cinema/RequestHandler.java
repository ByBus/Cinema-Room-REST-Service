package cinema;

import org.springframework.web.bind.annotation.*;
import utils.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
public class RequestHandler {
    @Autowired
    Hall hall;
    @Autowired
    TicketManager ticketManager;

    @GetMapping("/seats")
    public Hall getCinemaInfo() {
        return hall;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Map<String, Integer> request) {
        Seat seat = hall.getSeat(request.get("row"), request.get("column"));
        try {
            Checker.check(seat);
        } catch (Exception e) {
            return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        Ticket ticket = ticketManager.giveTicket(seat);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        try {
            Ticket returnedTicket = ticketManager.removeTicketWithToken(token);
            return new ResponseEntity<>(returnedTicket.withoutToken(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStatistics(@RequestParam(required = false) String password) {
        try {
            Checker.checkPassword(password);
            TicketManager.Statistics statistics = ticketManager.getStatistics();
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}
