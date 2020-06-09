package ru.vsu.cs.DTO;

import ru.vsu.cs.DTO.mappers.NewOrderMapper;
import ru.vsu.cs.Entities.Customer;
import ru.vsu.cs.Entities.Region;

import java.time.LocalDate;
import java.util.Objects;

public class InfoOrderDTO extends NewOrderDTO {

    private String status;

    public InfoOrderDTO() {
    }

    public InfoOrderDTO(Long id, Customer customer, Region region, double area, LocalDate date, String status) {
        super(id, customer, region, area, date);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InfoOrderDTO that = (InfoOrderDTO) o;
        return Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStatus());
    }

    @Override
    public String toString() {
        return "InfoOrderDTO{" +
                "status='" + status + '\'' +
                '}';
    }
}
