package ru.vsu.cs.DTO;

public class ChangeVolumePesticideDTO {

    private Long pesticideID;
    private double volume;

    public ChangeVolumePesticideDTO() {
    }

    public ChangeVolumePesticideDTO(Long pesticideID, double volume) {
        this.pesticideID = pesticideID;
        this.volume = volume;
    }

    public Long getPesticideID() {
        return pesticideID;
    }

    public void setPesticideID(Long pesticideID) {
        this.pesticideID = pesticideID;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ChangeVolumePesticideDTO{" +
                "pesticideID=" + pesticideID +
                ", volume=" + volume +
                '}';
    }
}