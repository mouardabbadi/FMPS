package com.example.fmps;

public class etudiant {

    public etudiant(String nom, String douar, String section) {
        this.nom = nom;
        this.douar = douar;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDouar() {
        return douar;
    }

    public void setDouar(String douar) {
        this.douar = douar;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    int id;
    String nom;
    String douar;
    String section;
}
