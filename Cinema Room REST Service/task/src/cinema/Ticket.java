package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Ticket {
    private final String token;
    @JsonProperty("ticket")
    private final Seat seat;

    public Ticket(Seat seat) {
        this.token = UUID.randomUUID().toString();
        this.seat = seat;
    }

    public NoToken withoutToken() {
        return new NoToken(this);
    }

    private static class NoToken {
        @JsonProperty("returned_ticket")
        private final Seat seat;

        private NoToken(Ticket ticket) {
            seat = ticket.seat;
        }
    }
}
