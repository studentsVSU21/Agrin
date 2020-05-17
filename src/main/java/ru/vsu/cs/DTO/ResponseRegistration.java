package ru.vsu.cs.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

public class ResponseRegistration {

    private String status;
    private String message;

    public ResponseRegistration() {
    }

    public ResponseRegistration(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseRegistration{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseRegistration that = (ResponseRegistration) o;
        return Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMessage());
    }
}
