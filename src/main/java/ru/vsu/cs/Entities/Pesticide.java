package ru.vsu.cs.Entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "pesticide")
public class Pesticide {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pesticide_id_generator")
    @SequenceGenerator(name = "pesticide_id_generator", sequenceName = "pesticide_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "volume")
    private double volume;

    public Pesticide() {
    }

    public Pesticide(@NotNull String name, @NotNull double volume) {
        this.name = name;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Pesticide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesticide pesticide = (Pesticide) o;
        return Double.compare(pesticide.getVolume(), getVolume()) == 0 &&
                Objects.equals(getId(), pesticide.getId()) &&
                Objects.equals(getName(), pesticide.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getVolume());
    }
}
