package com.example;

import java.time.LocalDate;
import java.util.List;

public class Employe {
    private String nom;
    private int id;
    private LocalDate dateEmbauche;
    private String numeroSecuriteSociale;
    private String poste;
    private List<Integer> projetsAssocies;

    public Employe(String nom, int id, LocalDate dateEmbauche, String numeroSecuriteSociale, String poste, List<Integer> projetsAssocies) {
        this.nom = nom;
        this.id = id;
        this.dateEmbauche = dateEmbauche;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.poste = poste;
        this.projetsAssocies = projetsAssocies;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public String getPoste() {
        return poste;
    }

    public boolean estAssocieAuProjet(Projet projet) {
        return projetsAssocies.contains(projet.getId());
    }
}


/*package com.example;

import java.time.LocalDate;

public class Employe {
    private String nom;
    private int id;
    private LocalDate dateEmbauche;
    private String numeroAssuranceSociale;
    private String poste;

    public Employe(String nom, int id, LocalDate dateEmbauche, String numeroAssuranceSociale, String poste) {
        this.nom = nom;
        this.id = id;
        this.dateEmbauche = dateEmbauche;
        this.numeroAssuranceSociale = numeroAssuranceSociale;
        this.poste = poste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getNumeroAssuranceSociale() {
        return numeroAssuranceSociale;
    }

    public void setNumeroAssuranceSociale(String numeroAssuranceSociale) {
        this.numeroAssuranceSociale = numeroAssuranceSociale;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
*/