package com.service_employes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.service_employes.outil.TypeContract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    private TypeContract typeContract;
    private Double salaire;
    private String urlFile;
    private LocalDate dateSortie;

    @OneToOne
    Employe employe;
}
