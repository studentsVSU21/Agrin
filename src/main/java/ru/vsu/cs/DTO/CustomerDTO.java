package ru.vsu.cs.DTO;

import java.util.Objects;

public class CustomerDTO {
    private String fio;
    private String phone;
    private String mail;

    public CustomerDTO() {
    }

    public CustomerDTO(String fio, String phone, String mail) {
        this.fio = fio;
        this.phone = phone;
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(getFio(), that.getFio()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getMail(), that.getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFio(), getPhone(), getMail());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
