package com.example; 
class PayInfo {
    public int employeId;
    public int heuresBase;
    public int heuresSupplementaires;
    public double tauxHoraireBase;
    public double tauxHoraireSupplementaire;
    public String periodeDebut;
    public String periodeFin;
    public Employe employe;

    public PayInfo(int employeId, int heuresBase, int heuresSupplementaires, double tauxHoraireBase, double tauxHoraireSupplementaire, String periodeDebut, String periodeFin, Employe employe) {
        this.employeId = employeId;
        this.heuresBase = heuresBase;
        this.heuresSupplementaires = heuresSupplementaires;
        this.tauxHoraireBase = tauxHoraireBase;
        this.tauxHoraireSupplementaire = tauxHoraireSupplementaire;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.employe = employe;
    }

    @Override
    public String toString() {
        return "Employe ID: " + employeId + ", Heures Base: " + heuresBase + ", Heures Supplementaires: " + heuresSupplementaires + ", Taux Horaire Base: " + tauxHoraireBase + ", Taux Horaire Supplementaire: " + tauxHoraireSupplementaire + ", Periode Debut: " + periodeDebut + ", Periode Fin: " + periodeFin;
    }
}