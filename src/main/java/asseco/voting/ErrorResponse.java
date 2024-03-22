package asseco.voting;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorResponse {
    @Schema(example= "2024-03-16T18:21:30.343+00:00")
    private String timestamp;
    private String status;
    private String error;
    private String message;
    private String path;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
