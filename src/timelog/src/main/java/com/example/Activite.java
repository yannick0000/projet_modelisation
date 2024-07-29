package com.example;

public class Activite {
    private String debut;
    private String fin;
    private Projet projet;
    private Discipline discipline;
    private Employe employe;

    public Activite(String debut, String fin, Projet projet, Discipline discipline, Employe employe) {
        this.debut = debut;
        this.fin = fin;
        this.projet = projet;
        this.discipline = discipline;
        this.employe = employe;
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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
