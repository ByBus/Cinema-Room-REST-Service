package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    @JsonProperty("error")
    private final String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
