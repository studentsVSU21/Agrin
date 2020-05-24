package ru.vsu.cs.DTO;

public class StatusResponse {

    private String status;

    public static StatusResponse successResponse(){
        return new StatusResponse("success");
    }

    public static StatusResponse failureResponse(){
        return new StatusResponse("failure");
    }

    public StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "status='" + status + '\'' +
                '}';
    }
}
