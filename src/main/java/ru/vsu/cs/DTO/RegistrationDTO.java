package ru.vsu.cs.DTO;

public class RegistrationDTO {

    private String password;
    private String fio;
    private String email;
    private String phoneNumber;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String password, String fio, String email, String phone_number) {
        this.password = password;
        this.fio = fio;
        this.email = email;
        this.phoneNumber = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }
}
