package com.example;

public class Projet {
    private String nom_Projet;
    private int id_projet;
    private String periodeDebut;
    private String periodeFin;
    private int heuresBudgetisees;

    public Projet(int id_projet, String nom_Projet, String periodeDebut, String periodeFin, int heuresBudgetisees) {
        this.nom_Projet = nom_Projet;
        this.id_projet = id_projet;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.heuresBudgetisees = heuresBudgetisees;
    }

    public String getNom() {
        return nom_Projet;
    }

    public void setNom(String nom_Projet) {
        this.nom_Projet = nom_Projet;
    }

    public int getId() {
        return id_projet;
    }

    public void setId(int id_projet) {
        this.id_projet = id_projet;
    }

    public String getPeriodeDebut() {
        return periodeDebut;
    }

    public void setPeriodeDebut(String periodeDebut) {
        this.periodeDebut = periodeDebut;
    }

    public String getPeriodeFin() {
        return periodeFin;
    }

    public void setPeriodeFin(String periodeFin) {
        this.periodeFin = periodeFin;
    }

    public int getHeuresBudgetisees() {
        return heuresBudgetisees;
    }

    public void setHeuresBudgetisees(int heuresBudgetisees) {
        this.heuresBudgetisees = heuresBudgetisees;
    }
}
