package ru.vsu.cs.DTO;

import java.util.Objects;

public class AddingPesticideDTO {

    private String name;
    private double volume;

    public AddingPesticideDTO() {
    }

    public AddingPesticideDTO(String name, double volume) {
        this.name = name;
        this.volume = volume;
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
        return "AddingPesticideDTO{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddingPesticideDTO that = (AddingPesticideDTO) o;
        return Double.compare(that.getVolume(), getVolume()) == 0 &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVolume());
    }
}