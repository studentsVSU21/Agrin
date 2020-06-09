package ru.vsu.cs.DTO;

public class OrderIdDTO {

    public Long orderId;

    public OrderIdDTO() {
    }

    public OrderIdDTO(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderIdDTO{" +
                "orderId=" + orderId +
                '}';
    }
}
