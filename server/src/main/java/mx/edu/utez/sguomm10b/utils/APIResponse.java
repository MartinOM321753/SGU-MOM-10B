package mx.edu.utez.sguomm10b.utils;

import org.springframework.http.HttpStatus;

public class APIResponse  {

    private Object data;
    private String message;
    private HttpStatus status;
    private Boolean error;

    public APIResponse(Object data, String message, HttpStatus status, Boolean error) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
