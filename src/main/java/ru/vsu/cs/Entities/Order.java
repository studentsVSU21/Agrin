package ru.vsu.cs.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "app_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "area")
    private double area;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    @ManyToMany
    @JoinTable(name = "pesticide_in_order",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "pesticide_id")}
    )
    private Collection<Pesticide> pesticides;

    public Order() {
    }

    public Order(Customer customer, Region region, double area, Progress progress) {
        this.customer = customer;
        this.region = region;
        this.area = area;
        this.progress = progress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<Pesticide> getPesticides() {
        return pesticides;
    }

    public void setPesticides(Collection<Pesticide> pesticides) {
        this.pesticides = pesticides;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", region=" + region +
                ", area=" + area +
                ", progress=" + progress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.getArea(), getArea()) == 0 &&
                Objects.equals(getId(), order.getId()) &&
                Objects.equals(getCustomer(), order.getCustomer()) &&
                Objects.equals(getRegion(), order.getRegion()) &&
                Objects.equals(getProgress(), order.getProgress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getRegion(), getArea(), getProgress());
    }
}