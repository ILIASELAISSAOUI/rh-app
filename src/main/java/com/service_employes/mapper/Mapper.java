package com.service_employes.mapper;

import com.service_employes.dtos.*;
import com.service_employes.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {
    public EmployeDTO fromEmploye(Employe employe){
        EmployeDTO employeDTO = new EmployeDTO();
        BeanUtils.copyProperties(employe,employeDTO);
        if(employe.getDepartement()!=null){
            employeDTO.setDepartement(fromDepartement(employe.getDepartement()));
        }
        if(employe.getContract()!=null){
            employeDTO.setContract(fromContract(employe.getContract()));
        }
        return employeDTO;
    }
    public Employe fromEmployeDTO(EmployeDTO employeDTO){
        Employe employe = new Employe();
        BeanUtils.copyProperties(employeDTO,employe);
        if(employeDTO.getDepartement()!=null){
            employe.setDepartement(fromDepartementDTO(employeDTO.getDepartement()));
        }
        if(employeDTO.getContract()!=null){
            employe.setContract(fromContractDTO(employeDTO.getContract()));
        }
        return employe;
    }
    public ContractDTO fromContract(Contract contract){
        ContractDTO contractDTO = new ContractDTO();
        BeanUtils.copyProperties(contract,contractDTO);
        return contractDTO;
    }
    public Contract fromContractDTO(ContractDTO contractDTO){
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO,contract,"dateDebut");
        return contract;
    }
    public DemandeCongeDTO fromDemandeConge(DemandeConge demandConge){
        DemandeCongeDTO demandCongeDTO = new DemandeCongeDTO();
        BeanUtils.copyProperties(demandConge,demandCongeDTO);
        return demandCongeDTO;
    }
    public DemandeConge fromDemandeCongeDTO(DemandeCongeDTO demandCongeDTO){
        DemandeConge demandConge = new DemandeConge();
        BeanUtils.copyProperties(demandCongeDTO,demandConge);
        return demandConge;
    }
    public DemandeTeletravailDTO fromDemandeTeletravail(DemandeTeletravail demandTeletravail){
        DemandeTeletravailDTO demandTeletravailDTO = new DemandeTeletravailDTO();
        BeanUtils.copyProperties(demandTeletravail,demandTeletravailDTO);
        return demandTeletravailDTO;
    }
    public DemandeTeletravail fromDemandeTeletravailDTO(DemandeTeletravailDTO demandTeletravailDTO){
        DemandeTeletravail demandeTeletravail= new DemandeTeletravail();
        BeanUtils.copyProperties(demandTeletravailDTO,demandeTeletravail);
        return demandeTeletravail;
    }
    public JourDTO fromJour(JourTeletravail jourTeletravail){
        JourDTO jourDTO = new JourDTO();
        BeanUtils.copyProperties(jourTeletravail,jourDTO);
        return jourDTO;
    }
    public JourTeletravail fromJourDTO(JourDTO jourDTO){
        JourTeletravail jourTeletravail = new JourTeletravail();
        BeanUtils.copyProperties(jourDTO,jourTeletravail);
        return jourTeletravail;
    }
    public Departement fromDepartementDTO(DepartementDTO departementDTO){
        Departement departement = new Departement();
        BeanUtils.copyProperties(departementDTO,departement);
        return departement;
    }
    public DepartementDTO fromDepartement(Departement departement){
        DepartementDTO departementDTO = new DepartementDTO();
        BeanUtils.copyProperties(departement,departementDTO);
        return departementDTO;
    }
}
