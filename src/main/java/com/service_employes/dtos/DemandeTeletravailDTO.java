package com.service_employes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class DemandeTeletravailDTO {
    long id;
    LocalDate dateDemande;
}
