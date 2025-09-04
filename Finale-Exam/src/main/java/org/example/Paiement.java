package org.example;

import java.time.Instant;

public class Paiement {
    public int id;
    public double montantPayé;
    public Instant datePaiement;

    public Paiement(int id, double montantPayé, Instant datePaiement) {
        this.id = id;
        this.montantPayé = montantPayé;
        this.datePaiement = datePaiement;
    }
}