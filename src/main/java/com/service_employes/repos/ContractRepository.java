package com.service_employes.repos;

import com.service_employes.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    public Contract findByEmploye_Id(long employeeId);
}
