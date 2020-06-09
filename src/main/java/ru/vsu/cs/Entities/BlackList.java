package ru.vsu.cs.Entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "blacklist")
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blacklist_id_generator")
    @SequenceGenerator(name = "blacklist_id_generator", sequenceName = "blacklist_id_seq", allocationSize = 1)
    @Column(name = "blacklist_id")
    private Long id;


    @Column(name = "")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    public BlackList() {
    }

    public BlackList(String email, String numberPhone) {
        this.email = email;
        this.numberPhone = numberPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return Objects.equals(getId(), blackList.getId()) &&
                Objects.equals(getEmail(), blackList.getEmail()) &&
                Objects.equals(getNumberPhone(), blackList.getNumberPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getNumberPhone());
    }
}