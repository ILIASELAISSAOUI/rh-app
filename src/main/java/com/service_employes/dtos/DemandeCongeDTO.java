package com.service_employes.dtos;

import com.service_employes.outil.EtatDemande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class DemandeCongeDTO {
    long id;
    LocalDate dateDemande;
    LocalDate dateDebut;
    LocalDate dateFin;
    EtatDemande etat;
    String motif;
}

