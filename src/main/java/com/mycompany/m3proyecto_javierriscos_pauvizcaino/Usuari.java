package com.mycompany.m3proyecto_javierriscos_pauvizcaino;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.image.Image;

 
public class Usuari {
 
    private String nif;
    private String nom;
    private LocalDate dataNaixement;
    private String telefon;
    private String correu;
    private Image imatgeJ;

    public Usuari(String nom) {
        this.nom = nom;
    }

    public Usuari(String nif, String nom, Date dataNaixement, String telefon, String correu) {
        this.nif = nif;
        this.nom = nom;
        this.dataNaixement = convertToLocalDate(dataNaixement);
        this.telefon = telefon;
        this.correu = correu;
    }

    public Usuari(String nif, String nom, LocalDate dataNaixement, String telefon, String correu) {
        this.nif = nif;
        this.nom = nom;
        this.dataNaixement = dataNaixement;
        this.telefon = telefon;
        this.correu = correu;
    }

    public Usuari(String nif, String nom, LocalDate dataNaixement, String telefon, String correu, Image imatgeJ) {
        this.nif = nif;
        this.nom = nom;
        this.dataNaixement = dataNaixement;
        this.telefon = telefon;
        this.correu = correu;
        this.imatgeJ = imatgeJ;
    }
   

    public Usuari(String nif, String nom, Date dataNaixement, String telefon, String correu, Image imatgeJ) {
        this.nif = nif;
        this.nom = nom;
        this.dataNaixement = convertToLocalDate(dataNaixement);
        this.telefon = telefon;
        this.correu = correu;
        this.imatgeJ = imatgeJ;
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getCorreu() {
        return correu;
    }

    public Image getImatgeJ() {
        return imatgeJ;
    }

    @Override
    public String toString() {
        return nom;
    }

}

