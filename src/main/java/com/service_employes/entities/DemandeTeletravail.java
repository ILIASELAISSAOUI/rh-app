package com.service_employes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class DemandeTeletravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    LocalDate dateDemande;
    boolean traite;

    @OneToMany(mappedBy = "demandeTeletravail", cascade = CascadeType.ALL, orphanRemoval = true)
    List<JourTeletravail> jourTeletravails;

    @ManyToOne
    Employe employe;
}


