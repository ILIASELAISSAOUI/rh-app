package com.service_employes.services;

import com.service_employes.dtos.EmployeDTO;
import com.service_employes.entities.Employe;
import com.service_employes.outil.StatusEmploye;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface EmployeService {

    public EmployeDTO getEmployeByDemandeConge(Long DemandeCongeId);
    public EmployeDTO getEmployeByEmail(String email);
    public Page<EmployeDTO> searchEmployes(String query, int page, int size);
    public EmployeDTO addEmploye(EmployeDTO employeDTO);
    public EmployeDTO deleteEmploye(Long id);
    public EmployeDTO getEmployeById(Long id);
    public Page<EmployeDTO> getEmployes(int page, int size);
    public EmployeDTO upgradeEmploye(Long id,EmployeDTO employeDTO); ;
}
