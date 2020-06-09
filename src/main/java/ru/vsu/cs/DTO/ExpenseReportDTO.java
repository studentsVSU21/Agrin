package ru.vsu.cs.DTO;

import java.util.Objects;

public class ExpenseReportDTO {

    private Long progressId;
    private Long pesticideId;
    private double volume;

    public ExpenseReportDTO() {
    }

    public ExpenseReportDTO(Long progressId, Long pesticideId, double volume) {
        this.progressId = progressId;
        this.pesticideId = pesticideId;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ExpenseReportDTO{" +
                "progressId=" + progressId +
                ", pesticideId=" + pesticideId +
                ", volume=" + volume +
                '}';
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    public Long getPesticideId() {
        return pesticideId;
    }

    public void setPesticideId(Long pesticideId) {
        this.pesticideId = pesticideId;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
