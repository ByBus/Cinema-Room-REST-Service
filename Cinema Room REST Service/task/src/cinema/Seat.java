package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean isPurchased = false;

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
