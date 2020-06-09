package ru.vsu.cs.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "expense_report")
public class ExpenseReport {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_report_id_generator")
    @SequenceGenerator(name = "expense_report_id_generator", sequenceName = "temp_progress_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pesticide_id")
    private Pesticide pesticide;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @NotNull
    @Column(name = "volume")
    private double volume;

    public ExpenseReport() {
    }

    public ExpenseReport(Progress progress, Pesticide pesticide, LocalDate reportDate, @NotNull double volume) {
        this.progress = progress;
        this.pesticide = pesticide;
        this.reportDate = reportDate;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ExpenseReport{" +
                "id=" + id +
                ", progress=" + progress +
                ", pesticide=" + pesticide +
                ", reportDate=" + reportDate +
                ", volume=" + volume +
                '}';
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Pesticide getPesticide() {
        return pesticide;
    }

    public void setPesticide(Pesticide pesticide) {
        this.pesticide = pesticide;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseReport that = (ExpenseReport) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getProgress(), that.getProgress()) &&
                Objects.equals(getPesticide(), that.getPesticide()) &&
                Objects.equals(getReportDate(), that.getReportDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProgress(), getPesticide(), getReportDate());
    }
}
