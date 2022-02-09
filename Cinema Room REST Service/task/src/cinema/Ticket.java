package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    private final String token;
    @JsonProperty("ticket")
    private final Seat seat;

    public Ticket(Seat seat) {
        this.token = UUID.randomUUID().toString();
        this.seat = seat;
    }

    public String getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }
}
