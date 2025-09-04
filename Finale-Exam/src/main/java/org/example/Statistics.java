package org.example;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Statistics {

    public static List<Frais> getLateFees(List<Frais> fees, Instant t) {
        return fees.stream()
            .filter(f -> f.getStatut(t) == Frais.StatutFrais.LATE)
            .collect(Collectors.toList());
    }

    public static double getTotalMissingFees(List<Frais> fees, Instant t) {
        return fees.stream()
            .filter(f -> f.getStatut(t) == Frais.StatutFrais.LATE)
            .mapToDouble(f -> f.montant - (f.paiements == null ? 0 : f.paiements.stream()
                .filter(p -> p.datePaiement.isBefore(t) || p.datePaiement.equals(t))
                .mapToDouble(p -> p.montantPayé)
                .sum()))
            .sum();
    }

    public static double getTotalPaidByStudent(Etudiant student, List<Frais> fees, Instant t) {
        return fees.stream()
            .filter(f -> f.etudiant != null && f.etudiant.equals(student))
            .flatMap(f -> f.paiements == null ? java.util.stream.Stream.empty() : f.paiements.stream())
            .filter(p -> p.datePaiement.isBefore(t) || p.datePaiement.equals(t))
            .mapToDouble(p -> p.montantPayé)
            .sum();
    }
}