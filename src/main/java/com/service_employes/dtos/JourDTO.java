package com.service_employes.dtos;

import com.service_employes.outil.EtatDemande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class JourDTO {
    private Long id;
    private LocalDate date;
    private EtatDemande etatDemande;
}
