package com.example.plaza_de_comidas.domain.model;
import java.time.LocalDate;

public class User { //ENTIDAD
    private int idUser; //VALUE OBJECT
    private String name; //VALUE OBJECT
    private String lastName; //VALUE OBJECT
    private String identificationDocument; //VALUE OBJECT
    private String phone;
    private LocalDate birthDate;
    private String gmail;
    private String password;
    private Rol idRol;

    public User(int idUser, String name, String lastName, String identificationDocument, String phone, LocalDate birthDate, String gmail, Rol idRol, String password) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.identificationDocument = identificationDocument;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gmail = gmail;
        this.idRol = idRol;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
