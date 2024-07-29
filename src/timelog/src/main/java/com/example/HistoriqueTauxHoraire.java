package com.example;

public class HistoriqueTauxHoraire {
    public double tauxHoraire;
    public double tauxHoraireSupplementaire;
    public String dateDebut;
    public String dateFin;

    public HistoriqueTauxHoraire(double tauxHoraire, double tauxHoraireSupplementaire, String dateDebut, String dateFin) {
        this.tauxHoraire = tauxHoraire;
        this.tauxHoraireSupplementaire = tauxHoraireSupplementaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
