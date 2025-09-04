package org.example;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private Instant dateEntree;
    private List<Groupe> historiqueGroupes = new ArrayList<>();

    public Etudiant(int id, String nom, String prenom, Instant dateEntree) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEntree = dateEntree;
    }
    public void changerGroupe(Groupe groupe) {
        historiqueGroupes.add(groupe);
    }
}