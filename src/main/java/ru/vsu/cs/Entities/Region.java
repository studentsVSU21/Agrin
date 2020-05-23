package ru.vsu.cs.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_generator")
    @SequenceGenerator(name = "region_id_generator", sequenceName = "region_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name_region")
    private String nameRegion;

    public Region(@NotNull String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public Region() {
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", nameRegion='" + nameRegion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
                Objects.equals(nameRegion, region.nameRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRegion);
    }
}
