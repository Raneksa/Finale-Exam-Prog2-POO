package org.example;

import java.time.Instant;
import java.util.List;

public class Frais {

    public enum StatutFrais {
        IN_PROGRESS,
        PAID,
        LATE,
        OVERPAID
    }

    public int id;
    public String label;
    public double montant;
    public Instant deadline;
    public List<Paiement> paiements;
    public Etudiant etudiant;

    public Frais(int id, String label, double montant, Instant deadline, Etudiant etudiant, List<Paiement> paiements) {
        this.id = id;
        this.label = label;
        this.montant = montant;
        this.deadline = deadline;
        this.etudiant = etudiant;
        this.paiements = paiements;
    }

    public StatutFrais getStatut(Instant instant) {
        double totalPayé = paiements == null ? 0 : paiements.stream()
            .filter(p -> p.datePaiement.isBefore(instant) || p.datePaiement.equals(instant))
            .mapToDouble(p -> p.montantPayé)
            .sum();

        if (totalPayé > montant) {
            return StatutFrais.OVERPAID;
        }
        if (totalPayé == montant) {
            return StatutFrais.PAID;
        }
        if (instant.isAfter(deadline) && totalPayé < montant) {
            return StatutFrais.LATE;
        }
        if (instant.isBefore(deadline) && totalPayé < montant) {
            return StatutFrais.IN_PROGRESS;
        }
        return StatutFrais.IN_PROGRESS;
    }
}