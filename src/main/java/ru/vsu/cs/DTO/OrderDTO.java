package ru.vsu.cs.DTO;

import java.util.Objects;

public class OrderDTO {
    private CustomerDTO customer;
    private double area;
    private Long regionID;

    public OrderDTO() {
    }

    public OrderDTO(CustomerDTO customer, Double area, Long regionID) {
        this.customer = customer;
        this.area = area;
        this.regionID = regionID;
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
        return Objects.equals(getCustomer(), orderDTO.getCustomer()) &&
                Objects.equals(getArea(), orderDTO.getArea()) &&
                Objects.equals(getRegionID(), orderDTO.getRegionID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getArea(), getRegionID());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "customer=" + customer +
                ", area=" + area +
                ", regionID=" + regionID +
                '}';
    }
}
