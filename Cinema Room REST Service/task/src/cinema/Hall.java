package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hall {
    @JsonProperty("total_rows")
    private final int totalRows;
    @JsonProperty("total_columns")
    private final int totalColumns;
    @JsonProperty("available_seats")
    private final List<Seat> availableSeats;

    public Hall(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = createSeats();
    }

    private List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= totalRows; row++) {
            int price = row <= 4 ? 10 : 8;
            for (int column = 1; column <= totalColumns; column++) {
                seats.add(new Seat(row, column, price));
            }
        }
        return Collections.unmodifiableList(seats);
    }

    public Seat getSeat(int row, int column) {
        return availableSeats.stream()
                .filter(s -> s.getRow() == row && s.getColumn() == column)
                .findFirst()
                .orElse(null);
    }

    public int size() {
        return totalRows * totalColumns;
    }
}
