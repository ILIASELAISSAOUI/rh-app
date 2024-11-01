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
public class DemandeConge{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    LocalDate dateDemande;
    LocalDate dateDebut;
    LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    EtatDemande etat;
    String motif;

    @ManyToOne
    Employe employe;
}
