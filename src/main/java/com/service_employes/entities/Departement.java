package com.service_employes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class Departement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;

    @OneToMany(mappedBy = "departement",cascade = CascadeType.ALL)
    List<Employe> employes;
}
