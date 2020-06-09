package ru.vsu.cs.DTO;

import java.time.LocalDate;

public class ResponseExpenseReportDTO {

    private String pesticideName;
    private double volume;
    private LocalDate reportDate;

    public ResponseExpenseReportDTO() {
    }

    public ResponseExpenseReportDTO(String pesticideName, double volume, LocalDate reportDate) {
        this.pesticideName = pesticideName;
        this.volume = volume;
        this.reportDate = reportDate;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "ResponseExpenseReportDTO{" +
                "pesticideName='" + pesticideName + '\'' +
                ", volume=" + volume +
                ", reportDate=" + reportDate +
                '}';
    }
}