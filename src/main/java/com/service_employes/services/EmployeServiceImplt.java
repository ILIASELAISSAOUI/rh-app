package com.service_employes.services;

import com.service_employes.dtos.DepartementDTO;
import com.service_employes.dtos.EmployeDTO;
import com.service_employes.entities.Contract;
import com.service_employes.entities.Departement;
import com.service_employes.entities.Employe;
import com.service_employes.mapper.Mapper;
import com.service_employes.outil.StatusEmploye;
import com.service_employes.outil.UserRequestDTO;
import com.service_employes.repos.ContractRepository;
import com.service_employes.repos.EmployeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImplt implements EmployeService {

    private EmployeRepository employeRepository;
    private Mapper mapper;

    public EmployeServiceImplt(EmployeRepository employeRepository,Mapper mapper) {
        this.employeRepository = employeRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeDTO addEmploye(EmployeDTO employeDTO){
        Employe employe = mapper.fromEmployeDTO(employeDTO);
        employe.getContract().setEmploye(employe);
        employeRepository.save(employe);
        return  mapper.fromEmploye(employe);
    }

    @Override
    public EmployeDTO deleteEmploye(Long id){
        Employe employe=employeRepository.findById(id).orElse(null);
        if (employe!=null){
            employe.setStatusEmploye(StatusEmploye.DEMISSIONE);
        }
        EmployeDTO employeDTO=mapper.fromEmploye(employe);
        return employeDTO;
    }

    @Override
    public EmployeDTO getEmployeById(Long id) {
        Employe employe= employeRepository.findById(id).orElse(null);
        return  mapper.fromEmploye(employe);
    }

    @Override
    public Page<EmployeDTO> getEmployes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employe> employePage = employeRepository.findAll(pageable);

        List<EmployeDTO> employeDTOs = employePage.stream()
                .map(employe -> mapper.fromEmploye(employe))
                .collect(Collectors.toList());

        return new PageImpl<>(employeDTOs, pageable, employePage.getTotalElements());
    }

    @Override
    public EmployeDTO getEmployeByDemandeConge(Long demandeCongeId) {
        Employe employe=employeRepository.getEmployeByDemandeCongesId(demandeCongeId);
        EmployeDTO employeDTO=mapper.fromEmploye(employe);
        return employeDTO;
    }

    @Override
    public EmployeDTO getEmployeByEmail(String email) {
        Employe employe=employeRepository.findEmployeByEmail(email);
        EmployeDTO employeDTO=mapper.fromEmploye(employe);
        return employeDTO;
    }

    public Page<EmployeDTO> searchEmployes(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employe> employePage = employeRepository.findByDepartementNomeContainingOrLastNameContaining(
                query, query, pageable);
        return employePage.map(employe -> mapper.fromEmploye(employe));
    }

    @Override
    @Transactional
    public EmployeDTO upgradeEmploye(Long id, EmployeDTO employeDTO) {
        Employe employeExistante = employeRepository.findById(id).orElse(null);
        if (employeExistante != null) {
            BeanUtils.copyProperties(employeDTO, employeExistante, "id");

            if (employeDTO.getDepartement() != null) {
                employeExistante.setDepartement(mapper.fromDepartementDTO(employeDTO.getDepartement()));
            }
            if (employeDTO.getContract() != null) {
                Contract contract = mapper.fromContractDTO(employeDTO.getContract());
                contract.setEmploye(employeExistante);
                employeExistante.setContract(contract);
            }
            employeRepository.save(employeExistante);
        }
        return mapper.fromEmploye(employeExistante);
    }

}
