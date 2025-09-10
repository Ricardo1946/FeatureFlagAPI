package FeatureFlagAPI.ApiFlag.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String erroCode;
    private String path;
    private List<String> errors;

    public ErrorResponse(LocalDateTime timestamp, String message, int status, String erroCode, String path, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
        this.erroCode = erroCode;
        this.path = path;
        this.errors = errors;
    }
}
