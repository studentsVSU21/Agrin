package ru.vsu.cs.DTO;

public class InfoUserDTO {

    private Long userId;
    private String email;
    private String phoneNumber;
    private String fio;

    public InfoUserDTO() {
    }

    public InfoUserDTO(Long userId, String email, String phoneNumber, String fio) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fio = fio;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "InfoUserDTO{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
