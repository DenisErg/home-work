package sk.edenis.homeworkunion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {

    private String error;
    private Map<String, String> errors;

    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(String error) {
        this.error = error;
    }

    public ErrorResponseDTO(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
