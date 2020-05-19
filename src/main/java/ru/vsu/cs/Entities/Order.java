package ru.vsu.cs.Entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "app_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq", allocationSize = 1)
    @Column(name = "Progress_ID")
    private Long id;

    @Column(name = "phone_number")
    @NotNull
    private String phone;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "area")
    private double area;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    public Order() {
    }

    public Order(String phone, String email, double area, Progress progress) {
        this.phone = phone;
        this.email = email;
        this.area = area;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
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
                Objects.equals(getPhone(), order.getPhone()) &&
                Objects.equals(getEmail(), order.getEmail()) &&
                Objects.equals(getProgress(), order.getProgress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhone(), getEmail(), getArea(), getProgress());
    }
}
