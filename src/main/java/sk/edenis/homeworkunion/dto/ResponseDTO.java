package sk.edenis.homeworkunion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private String message;
    private Object response;

    public ResponseDTO() {
    }

    public ResponseDTO(String response) {
        this.response = response;
    }

    public ResponseDTO(Object response) {
        this.response = response;
    }

    public ResponseDTO(String message, Object response) {
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
