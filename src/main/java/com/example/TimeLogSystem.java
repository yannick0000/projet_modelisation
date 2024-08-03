
package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class TimeLogSystem {
    private static final String FILE_NAME = "activities.json";
    private static List<Discipline> disciplines = new ArrayList<>();
    private static List<Projet> projets = new ArrayList<>();
    private static List<Employe> employes = new ArrayList<>();

    public static void main(String[] args) {
        chargerDonnees();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre nom:");
        String nom = scanner.nextLine();
        System.out.println("Entrez votre ID:");
        int id = scanner.nextInt();
        scanner.nextLine();  

        try {
            Employe employe = trouverEmployeParIdEtNom(id, nom);
            mainLoop(scanner, employe);
        } catch (EmployeNotFoundException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    private static void mainLoop(Scanner scanner, Employe employe) {
        boolean running = true;
        while (running) {
            System.out.println("Voulez-vous (1) débuter une activité, (2) terminer une activité, ou (3) quitter?");
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    debuterActivite(employe, scanner);
                    break;
                case 2:
                    terminerActivite(employe);
                    break;
                case 3:
                    running = false;
                    System.out.println("Quitter le système.");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }

    private static Employe trouverEmployeParIdEtNom(int id, String nom) throws EmployeNotFoundException {
        for (Employe employe : employes) {
            if (employe.getId() == id && employe.getNom().trim().equalsIgnoreCase(nom.trim())) {
                return employe;
            }
        }
        throw new EmployeNotFoundException("Employé avec cet ID et nom non trouvé.");
    }

    private static void debuterActivite(Employe employe, Scanner scanner) {
        for (Discipline activite : disciplines) {
            if (activite.getEmploye() != null && activite.getEmploye().getId() == employe.getId() && activite.getFin() == null) {
                System.out.println("Vous avez déjà une activité en cours. Veuillez la terminer avant d'en commencer une nouvelle.");
                return;
            }
        }

        afficherProjets();
        System.out.println("Sélectionnez l'ID du projet:");
        int projetId = scanner.nextInt();
        scanner.nextLine();  

        Projet projet = trouverProjetParId(projetId);
        if (projet == null) {
            System.out.println("Projet non trouvé.");
            return;
        }

        if (!employe.estAssocieAuProjet(projet)) {
            System.out.println("Vous n'êtes pas associé à ce projet. Veuillez choisir un projet valide.");
            return;
        }

        afficherDisciplines();
        System.out.println("Sélectionnez l'ID de la discipline:");
        int disciplineId = scanner.nextInt();
        scanner.nextLine();  

        Discipline discipline = trouverDisciplineParId(disciplineId);
        if (discipline == null) {
            System.out.println("Discipline non trouvée.");
            return;
        }

        LocalDateTime debut = obtenirHeureActuelle();
        Discipline activite = new Discipline(disciplineId, discipline.getNom(), debut.toString(), null, projet, employe);
        disciplines.add(activite);

        afficherInformationsUtilisateur(employe, "Débutée");
        sauvegarderDonnees();
    }

    private static void terminerActivite(Employe employe) {
        LocalDateTime fin = obtenirHeureActuelle(); // Obtenir l'heure actuelle
        boolean found = false;

        for (Discipline activite : disciplines) {
            if (activite.getEmploye() != null && activite.getEmploye().getId() == employe.getId() && activite.getFin() == null) {
                activite.setFin(fin.toString()); 
                found = true;
                afficherInformationsUtilisateur(employe, "Terminée");
                afficherTempsPasse(activite);
                break;
            }
        }

        if (!found) {
            System.out.println("Aucune activité en cours trouvée pour cet employé.");
        }

        sauvegarderDonnees(); 
    }

    private static void afficherTempsPasse(Discipline activite) {
        if (activite.getDebut() != null && activite.getFin() != null) {
            LocalDateTime debut = LocalDateTime.parse(activite.getDebut());
            LocalDateTime fin = LocalDateTime.parse(activite.getFin());
            Duration duree = Duration.between(debut, fin);
            long heures = duree.toHours();
            long minutes = duree.toMinutes() % 60;
            System.out.println("Temps passé sur l'activité: " + heures + " heures et " + minutes + " minutes.");
        }
    }

    private static void sauvegarderDonnees() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            List<Discipline> activitesFiltrees = new ArrayList<>();
            for (Discipline discipline : disciplines) {
                if (discipline.getProjet() != null && discipline.getEmploye() != null) {
                    activitesFiltrees.add(discipline);
                }
            }
            gson.toJson(activitesFiltrees, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void chargerDonnees() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type type = new TypeToken<List<Discipline>>() {}.getType();
            List<Discipline> loadedDisciplines = gson.fromJson(reader, type);
            if (loadedDisciplines != null) {
                disciplines = loadedDisciplines;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (employes.isEmpty() || projets.isEmpty() || disciplines.isEmpty()) {
            initialiserDonneesParDefaut();
        }
    }

    private static void initialiserDonneesParDefaut() {
        employes.clear();
        projets.clear();
        disciplines.clear();


        employes.add(new Employe("Employe1", 1, LocalDate.now(), "123-45-6789", "Poste1", Arrays.asList(1, 2)));
        employes.add(new Employe("Employe2", 2, LocalDate.now(), "234-56-7890", "Poste2", Arrays.asList(3, 4)));
        employes.add(new Employe("Cedric", 3, LocalDate.now(), "345-67-8901", "Poste3", Arrays.asList(5, 6)));

        projets.add(new Projet(1, "Application de gestion de taches (To-Do List)", "2023-01-01", "2023-12-31", 1000));
        projets.add(new Projet(2, "Systeme de gestion de bibliotheque", "2023-01-01", "2023-12-31", 2000));
        projets.add(new Projet(3, "Plateforme de blog", "2023-01-01", "2023-12-31", 1500));
        projets.add(new Projet(4, "Application de suivi des depenses", "2023-01-01", "2023-12-31", 800));
        projets.add(new Projet(5, "Systeme de reservation de billets (Event Ticketing)", "2023-01-01", "2023-12-31", 1200));
        projets.add(new Projet(6, "Application de gestion de notes", "2023-01-01", "2023-12-31", 900));
        projets.add(new Projet(7, "Système de gestion de contacts ", "2023-01-01", "2023-12-31", 1100));
        projets.add(new Projet(8, "Application de recettes de cuisine", "2023-01-01", "2023-12-31", 1300));
        projets.add(new Projet(9, "Systeme de gestion des employés", "2023-01-01", "2023-12-31", 1400));
        projets.add(new Projet(10, "Application de quiz en ligne", "2023-01-01", "2023-12-31", 1600));

        disciplines.add(new Discipline(1, "Modélisation métier", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(2, "Exigences", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(3, "Conception", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(4, "Implémentation", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(5, "Déploiement", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(6, "Gestion de projet", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(7, "Gestion de la configuration et des changements", "2024-07-31T01:23:45", null, null, null));
        disciplines.add(new Discipline(8, "Environnement", "2024-07-31T01:23:45", null, null, null));
    }

    private static void afficherProjets() {
        System.out.println("Projets disponibles:");
        for (Projet projet : projets) {
            System.out.println(projet.getId() + ": " + projet.getNom());
        }
    }

    private static Projet trouverProjetParId(int id) {
        for (Projet projet : projets) {
            if (projet.getId() == id) {
                return projet;
            }
        }
        return null;
    }

    private static void afficherDisciplines() {
        System.out.println("Disciplines disponibles:");
        for (Discipline discipline : disciplines) {
            System.out.println(discipline.getId() + ": " + discipline.getNom());
        }
    }

    private static Discipline trouverDisciplineParId(int id) {
        for (Discipline discipline : disciplines) {
            if (discipline.getId() == id) {
                return discipline;
            }
        }
        return null;
    }

    private static void afficherInformationsUtilisateur(Employe employe, String statut) {
        System.out.println("Statut de l'utilisateur:");
        System.out.println("Nom: " + employe.getNom());
    }

    private static LocalDateTime obtenirHeureActuelle() {
        return LocalDateTime.now();
    }
}

