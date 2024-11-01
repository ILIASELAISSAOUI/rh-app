package com.service_employes.repos;

import com.service_employes.entities.Employe;
import com.service_employes.outil.StatusEmploye;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
    public List<Employe> findByDepartementId(long departementId);
    public Employe findEmployeByEmail(String email);
    public Page<Employe> findByDepartementNomeContainingOrLastNameContaining(
            String firstName, String departementName, Pageable pageable);
    public Employe getEmployeByDemandeCongesId(long demandeCongesId);
}
