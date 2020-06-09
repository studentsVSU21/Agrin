package ru.vsu.cs.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "app_progress")
public class Progress{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progress_id_generator")
    @SequenceGenerator(name = "progress_id_generator", sequenceName = "progress_id_seq", allocationSize = 1)
    @Column(name = "progress_id")
    private Long progressID;

    @Column(name = "date_start", columnDefinition = "DATE default null")
    private LocalDate dateStart;

    @Column(name="date_end", columnDefinition = "DATE default null")
    private LocalDate dateEnd;

    @NotNull
    @Column(name = "processed_area")
    private Double processedArea;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status")
    private Status status;

    public Progress() {
    }

    public Progress(LocalDate dateStart, LocalDate dateEnd, @NotNull Double processedArea, @NotNull Status status) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.processedArea = processedArea;
        this.status = status;
    }

    public Long getProgressID() {
        return progressID;
    }

    public void setProgressID(Long progressID) {
        this.progressID = progressID;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getProcessedArea() {
        return processedArea;
    }

    public void setProcessedArea(Double processedArea) {
        this.processedArea = processedArea;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "progressID=" + progressID +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", processedArea=" + processedArea +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return Objects.equals(getProgressID(), progress.getProgressID()) &&
                Objects.equals(getDateStart(), progress.getDateStart()) &&
                Objects.equals(getDateEnd(), progress.getDateEnd()) &&
                Objects.equals(getProcessedArea(), progress.getProcessedArea()) &&
                Objects.equals(getStatus(), progress.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProgressID(), getDateStart(), getDateEnd(), getProcessedArea(), getStatus());
    }
}
