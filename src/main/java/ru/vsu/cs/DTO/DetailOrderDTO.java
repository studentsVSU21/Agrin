package ru.vsu.cs.DTO;

import ru.vsu.cs.Entities.Progress;

public class DetailOrderDTO {

    public InfoOrderDTO infoOrder;
    public Progress progress;

    public DetailOrderDTO() {
    }

    public DetailOrderDTO(InfoOrderDTO infoOrder, Progress progress) {
        this.infoOrder = infoOrder;
        this.progress = progress;
    }

    public InfoOrderDTO getInfoOrder() {
        return infoOrder;
    }

    public void setInfoOrder(InfoOrderDTO infoOrder) {
        this.infoOrder = infoOrder;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "DetailOrderDTO{" +
                "infoOrder=" + infoOrder +
                ", progress=" + progress +
                '}';
    }
}
