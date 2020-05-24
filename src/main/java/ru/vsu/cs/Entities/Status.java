package ru.vsu.cs.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status_order")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_order_generator")
    @SequenceGenerator(name = "status_order_generator", sequenceName = "status_order_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_status")
    private String nameStatus;

    public Status() {
    }

    public Status(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(getId(), status.getId()) &&
                Objects.equals(getNameStatus(), status.getNameStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameStatus());
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", nameStatus='" + nameStatus + '\'' +
                '}';
    }
}
