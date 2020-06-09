package ru.vsu.cs.DTO;

public class ChangeProcessAreaDTO {

    private Long orderID;
    private double processArea;

    public ChangeProcessAreaDTO() {
    }

    public ChangeProcessAreaDTO(Long orderID, double processArea) {
        this.orderID = orderID;
        this.processArea = processArea;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public double getProcessArea() {
        return processArea;
    }

    public void setProcessArea(double processArea) {
        this.processArea = processArea;
    }

    @Override
    public String toString() {
        return "ChangeProcessAreaDTO{" +
                "orderID=" + orderID +
                ", processArea=" + processArea +
                '}';
    }
}
