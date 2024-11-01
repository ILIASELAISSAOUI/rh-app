package com.service_employes.services;

import com.service_employes.dtos.DepartementDTO;
import com.service_employes.entities.Departement;
import com.service_employes.entities.Employe;
import com.service_employes.exception.DepartementNotEmptyException;
import com.service_employes.mapper.Mapper;
import com.service_employes.repos.DepartementRepository;
import com.service_employes.repos.EmployeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartementServiceImplt implements DepartementService{
    private final EmployeRepository employeRepository;
    private DepartementRepository departementRepository;
    private Mapper mapper;

    public DepartementServiceImplt(DepartementRepository departementRepository,
                                   Mapper mapper, EmployeRepository employeRepository) {
        this.departementRepository = departementRepository;
        this.mapper = mapper;
        this.employeRepository = employeRepository;
    }

    @Override
    public DepartementDTO addDepartement(DepartementDTO departement) {
        Departement departement1=mapper.fromDepartementDTO(departement);
        departementRepository.save(departement1);
        return departement;
    }

    @Override
    public List<DepartementDTO> getAllDepartements() {
        List<Departement> departements=departementRepository.findAll();
        List<DepartementDTO> departementDTO=departements.stream().
                map(departement -> mapper.fromDepartement(departement)).
                collect(Collectors.toList());
        return departementDTO;
    }

    @Override
    public void deleteDepartement(long id){
        List<Employe> employes = employeRepository.findByDepartementId(id);
        if (!employes.isEmpty()) {
            throw new DepartementNotEmptyException("Cannot delete department. It still has employees.");
        }
        departementRepository.deleteById(id);
    }
}
