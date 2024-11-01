package com.service_employes.dtos;

import com.service_employes.outil.TypeContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class ContractDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeContract typeContract;
    private Double salaire;
    private String urlFile;
    private LocalDate dateSortie;
}
