package com.service_employes.repos;

import com.service_employes.entities.DemandeTeletravail;
import com.service_employes.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeTeletravailRepository extends JpaRepository<DemandeTeletravail, Long> {
    public List<DemandeTeletravail> findAllByEmployeId(Long employeId);
}
