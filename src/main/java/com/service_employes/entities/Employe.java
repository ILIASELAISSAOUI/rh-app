package com.service_employes.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service_employes.outil.StatusEmploye;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    @Enumerated(EnumType.STRING)
    private StatusEmploye statusEmploye;

    @OneToOne(mappedBy = "employe",cascade = CascadeType.ALL)
    private Contract contract;

    @OneToMany(mappedBy = "employe")
    List<DemandeTeletravail> demandeTeletravails;

    @OneToMany(mappedBy = "employe")
    List<DemandeConge> demandeConges;

    @ManyToOne
    Departement departement;


}
