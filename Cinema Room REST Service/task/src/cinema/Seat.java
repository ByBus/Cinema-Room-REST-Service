package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean isPurchased = false;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    @JsonIgnore
    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased() {
        isPurchased = true;
    }

    public void setNonPurchased() {
        isPurchased = false;
    }
}
