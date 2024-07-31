package com.example;

public class Discipline {
    private int id;
    private String nom;
    private String debut;
    private String fin;
    private Projet projet;
    private Employe employe;
    

    public Discipline(int id, String nom, String debut, String fin, Projet projet, Employe employe) {
        this.id = id;
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.projet = projet;
        this.employe = employe;
    }
    public int getId(){
        return id;
    }
    public String getNom() {
        return nom;
    }
  
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
