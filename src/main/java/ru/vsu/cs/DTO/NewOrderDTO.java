package ru.vsu.cs.DTO;

import ru.vsu.cs.Entities.Customer;
import ru.vsu.cs.Entities.Region;

import java.time.LocalDate;
import java.util.Objects;

public class NewOrderDTO {
    private Long id;
    private Customer customer;
    private Region region;
    private double area;
    private LocalDate date;

    public NewOrderDTO() {
    }

    public NewOrderDTO(Long id, Customer customer, Region region, double area, LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.region = region;
        this.area = area;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "NewOrderDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", region=" + region +
                ", area=" + area +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewOrderDTO that = (NewOrderDTO) o;
        return Double.compare(that.getArea(), getArea()) == 0 &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCustomer(), that.getCustomer()) &&
                Objects.equals(getRegion(), that.getRegion()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getRegion(), getArea(), getDate());
    }
}