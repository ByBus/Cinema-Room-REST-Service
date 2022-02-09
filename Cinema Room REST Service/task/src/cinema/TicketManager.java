package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import utils.Checker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketManager {
    private final Map<String, Ticket> soldTickets = new ConcurrentHashMap<>();
    @Autowired
    Hall hall;

    public Ticket giveTicket(Seat seat) {
        Ticket ticket = new Ticket(seat);
        soldTickets.put(ticket.getToken(), ticket);
        ticket.getSeat().setPurchased();
        return ticket;
    }

    public Ticket removeTicketWithToken(String token) {
        Ticket returnedTicket = soldTickets.remove(token);
        Checker.check(returnedTicket);
        returnedTicket.getSeat().setNonPurchased();
        return returnedTicket;
    }

    public Statistics getStatistics() {
        int income = calculateIncome();
        int purchasedTickets = soldTickets.size();
        int availableSeats = hall.size() - purchasedTickets;
        return new Statistics(income, availableSeats, purchasedTickets);
    }

    private int calculateIncome() {
        return soldTickets.values().stream()
                .mapToInt(t -> t.getSeat().getPrice())
                .sum();
    }

    @Getter
    @AllArgsConstructor
    static class Statistics {
        @JsonProperty("current_income")
        private final int income;
        @JsonProperty("number_of_available_seats")
        private final int availableSeats;
        @JsonProperty("number_of_purchased_tickets")
        private final int purchasedTickets;
    }
}
