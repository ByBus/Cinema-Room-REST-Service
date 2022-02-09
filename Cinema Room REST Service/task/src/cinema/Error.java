package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    @JsonProperty("error")
    private final String errorMessage;
}
