package com.service_employes.repos;

import com.service_employes.entities.DemandeConge;
import com.service_employes.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandesCongesRepository extends JpaRepository <DemandeConge,Long>{
    public List<DemandeConge> findByEmploye_Id(Long id);
}
