package ru.vsu.cs.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {
    private CustomerDTO customer;
    private double area;
    private Long regionID;
    private LocalDate date;

    public OrderDTO() {
    }

    public OrderDTO(CustomerDTO customer, double area, Long regionID, LocalDate date) {
        this.customer = customer;
        this.area = area;
        this.regionID = regionID;
        this.date = date;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getRegionID() {
        return regionID;
    }

    public void setRegionID(Long regionID) {
        this.regionID = regionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Double.compare(orderDTO.getArea(), getArea()) == 0 &&
                Objects.equals(getCustomer(), orderDTO.getCustomer()) &&
                Objects.equals(getRegionID(), orderDTO.getRegionID()) &&
                Objects.equals(date, orderDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getArea(), getRegionID(), date);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "customer=" + customer +
                ", area=" + area +
                ", regionID=" + regionID +
                ", date=" + date +
                '}';
    }
}
