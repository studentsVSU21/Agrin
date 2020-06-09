package ru.vsu.cs.DTO;

import java.time.LocalDate;

public class NotConfirmedOrderDTO {

    private Long orderId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long pesticideId;

    public NotConfirmedOrderDTO() {
    }

    public NotConfirmedOrderDTO(Long orderId, LocalDate startDate, LocalDate endDate, Long pesticideId) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pesticideId = pesticideId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getPesticideId() {
        return pesticideId;
    }

    public void setPesticideId(Long pesticideId) {
        this.pesticideId = pesticideId;
    }

    @Override
    public String toString() {
        return "NotConfirmedOrderDTO{" +
                "orderId=" + orderId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", pesticideId=" + pesticideId +
                '}';
    }
}