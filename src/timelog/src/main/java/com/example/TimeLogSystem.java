package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.reflect.TypeToken;

public class TimeLogSystem {
    private static final String FILE_NAME = "activities.json";
    private static List<Activite> activites = new ArrayList<>();
    private static List<Projet> projets = new ArrayList<>();
    private static List<Discipline> disciplines = new ArrayList<>();

    public static void main(String[] args) {
        chargerDonnees(); // Charger les activités, projets et disciplines existantes depuis le fichier JSON

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre nom:");
        String nom = scanner.nextLine();
        System.out.println("Entrez votre ID:");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne

        Employe employe = new Employe(nom, id);

        System.out.println("Voulez-vous (1) débuter une activité ou (2) terminer une activité?");
        int choix = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne

        switch (choix) {
            case 1:
                debuterActivite(employe, scanner);
                break;
            case 2:
                terminerActivite(employe, scanner);
                break;
            default:
                System.out.println("Choix invalide.");
                break;
        }

        scanner.close();
    }

    private static void debuterActivite(Employe employe, Scanner scanner) {
        afficherProjets();
        System.out.println("Sélectionnez l'ID du projet:");
        int projetId = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne

        Projet projet = trouverProjetParId(projetId);
        if (projet == null) {
            System.out.println("Projet non trouve.");
            return;
        }

        afficherDisciplines();
        System.out.println("Sélectionnez le nom de la discipline:");
        String disciplineNom = scanner.nextLine();

        Discipline discipline = trouverDisciplineParNom(disciplineNom);
        if (discipline == null) {
            System.out.println("Discipline non trouvée.");
            return;
        }

        String debut = obtenirHeureActuelle();

        Activite activite = new Activite(debut, null, projet, discipline, employe);
        activites.add(activite);

        afficherInformationsUtilisateur(employe, "Débuté");

        sauvegarderDonnees();
    }

    private static void terminerActivite(Employe employe, Scanner scanner) {
        String fin = obtenirHeureActuelle();
        boolean found = false;

        for (Activite activite : activites) {
            if (activite.getEmploye().getId() == employe.getId() && activite.getFin() == null) {
                activite.setFin(fin);
                found = true;
                afficherInformationsUtilisateur(employe, "Terminé");
                break;
            }
        }

        if (!found) {
            System.out.println("Aucune activité en cours trouvée pour cet employé.");
        }

        sauvegarderDonnees();
    }

    private static void sauvegarderDonnees() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(activites, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void chargerDonnees() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Activite>>() {}.getType();
            List<Activite> loadedActivites = gson.fromJson(reader, type);
            if (loadedActivites != null) {
                activites = loadedActivites;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        projets.add(new Projet(1, "Application de gestion de tâches (To-Do List)"));
        projets.add(new Projet(2, "Système de gestion de bibliothèque"));
        projets.add(new Projet(3, "Plateforme de blog"));
        projets.add(new Projet(4, "Application de suivi des dépenses"));
        projets.add(new Projet(5, "Système de réservation de billets (Event Ticketing)"));
        projets.add(new Projet(6, "Application de gestion de notes"));
        projets.add(new Projet(7, "Système de gestion de contacts (CRM)"));
        projets.add(new Projet(8, "Application de recettes de cuisine"));
        projets.add(new Projet(9, "Système de gestion des employés"));
        projets.add(new Projet(10, "Application de quiz en ligne"));


        disciplines.add(new Discipline("Modelisation metier"));;
        disciplines.add(new Discipline("Exigences"));
        disciplines.add(new Discipline("Conception"));
        disciplines.add(new Discipline("implementation"));
        disciplines.add(new Discipline("Deploiement"));
        disciplines.add(new Discipline("Gestion de projet"));
        disciplines.add(new Discipline("Gestion de la configuration et des changements"));
        disciplines.add(new Discipline("Environnement"));



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
            System.out.println(discipline.getNom());
        }
    }

    private static Discipline trouverDisciplineParNom(String nom) {
        for (Discipline discipline : disciplines) {
            if (discipline.getNom().equals(nom)) {
                return discipline;
            }
        }
        return null;
    }

    private static void afficherInformationsUtilisateur(Employe employe, String statut) {
        System.out.println("Statut de l'utilisateur:");
        System.out.println("Nom: " + employe.getNom());
        System.out.println("ID: " + employe.getId());
        System.out.println("Statut: " + statut);
    }

    private static String obtenirHeureActuelle() {
        LocalDateTime maintenant = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return maintenant.format(format);
    }
}
