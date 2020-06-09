package ru.vsu.cs.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "App_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "User_ID")
    private Long userId;


    @Column(name="ROLE_USER")
    private String roleUser;

    @Column(name = "password")
    private String password;

    @Column(name="fio")
    private String fio;

    @Column(name="email")
    private String email;


    @Column(name="phone_number")
    private String phoneNumber;


    public User() {
    }

    @OneToMany
    @JoinTable(name = "user_orders",
                joinColumns = {@JoinColumn(name = "user_id")},
                inverseJoinColumns = {@JoinColumn(name = "order_id")}
                )
    private Collection<Order> orders;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleUser='" + roleUser + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getRoleUser(), user.getRoleUser()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFio(), user.getFio()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPhoneNumber(), user.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getRoleUser(), getPassword(), getFio(), getEmail(), getPhoneNumber());
    }
}
