package com.service_employes.repos;

import com.service_employes.entities.JourTeletravail;
import com.service_employes.outil.EtatDemande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JourRepository extends JpaRepository<JourTeletravail,Long> {
    public List<JourTeletravail> findAllByEtatDemandeAndDate(EtatDemande etat, LocalDate date);
    public List<JourTeletravail> findAllByDemandeTeletravailId(Long demandeTeletravailId);
}
