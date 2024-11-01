package com.service_employes.services;

import com.service_employes.dtos.DepartementDTO;
import com.service_employes.entities.Departement;

import java.util.List;

public interface DepartementService {
    public DepartementDTO addDepartement(DepartementDTO departement);
    public List<DepartementDTO> getAllDepartements();
    public void deleteDepartement(long id);
}
