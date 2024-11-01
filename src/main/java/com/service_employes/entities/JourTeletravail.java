package com.service_employes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.service_employes.outil.EtatDemande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor

public class JourTeletravail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String motif;
    @Enumerated(EnumType.STRING)
    private EtatDemande etatDemande;
    @ManyToOne
    private DemandeTeletravail demandeTeletravail;

}
