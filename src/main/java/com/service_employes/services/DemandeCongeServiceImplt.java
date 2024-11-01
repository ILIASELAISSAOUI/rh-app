package com.service_employes.services;

import com.service_employes.dtos.DemandeCongeDTO;
import com.service_employes.dtos.EmployeDTO;
import com.service_employes.entities.DemandeConge;
import com.service_employes.entities.Employe;
import com.service_employes.mapper.Mapper;
import com.service_employes.outil.EtatDemande;
import com.service_employes.repos.DemandesCongesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandeCongeServiceImplt implements DemandeCongesService{

    private DemandesCongesRepository demandesCongesRepository;
    private EmployeService employeService;
    private Mapper mapper;

    public DemandeCongeServiceImplt(DemandesCongesRepository demandesCongesRepository,
                                    Mapper mapper,
                                    EmployeService employeService) {
        this.demandesCongesRepository = demandesCongesRepository;
        this.employeService=employeService;
        this.mapper = mapper;
    }

    @Override
    public DemandeCongeDTO addDemandeConge(Long employeId,DemandeCongeDTO demandeCongeDTO) {
        LocalDate today=LocalDate.now();
        DemandeConge demandeConge=mapper.fromDemandeCongeDTO(demandeCongeDTO);
        EmployeDTO employeDTO=employeService.getEmployeById(employeId);
        Employe employe=mapper.fromEmployeDTO(employeDTO);
        demandeConge.setEmploye(employe);
        demandeConge.setDateDemande(today);
        demandesCongesRepository.save(demandeConge);
        return mapper.fromDemandeConge(demandeConge);
    }


    @Override
    public List<DemandeCongeDTO> getDemandeCongesByEmploye(Long employeId) {
        List<DemandeConge> demandeConges= demandesCongesRepository.findByEmploye_Id(employeId);
        List<DemandeCongeDTO> demandeCongeDTO=demandeConges.stream().
                map(demandeConge -> mapper.fromDemandeConge(demandeConge)).
                collect(Collectors.toList());
        return demandeCongeDTO;
    }

    @Override
    public List<DemandeCongeDTO> getDemandeConges() {
        List<DemandeConge> demandeConges= demandesCongesRepository.findAll();
        List<DemandeCongeDTO> demandeCongeDTO=demandeConges.stream().
                map(demandeConge -> mapper.fromDemandeConge(demandeConge)).
                collect(Collectors.toList());
        return demandeCongeDTO;
    }

    @Override
    public DemandeCongeDTO ApprouveDemande(Long id) {
        DemandeConge demandeConge= demandesCongesRepository.findById(id).orElse(null);
        if(demandeConge != null) {
            demandeConge.setEtat(EtatDemande.APPROUVE);
        }
        demandesCongesRepository.save(demandeConge);
        return mapper.fromDemandeConge(demandeConge);
    }

    @Override
    public DemandeCongeDTO refuserDemande(Long id,String motif) {
        DemandeConge demandeConge= demandesCongesRepository.findById(id).orElse(null);
        if(demandeConge != null) {
            demandeConge.setEtat(EtatDemande.REJETE);
        }
        if(motif!=" ") {
            demandeConge.setMotif(motif);
        }
        demandesCongesRepository.save(demandeConge);
        return mapper.fromDemandeConge(demandeConge);
    }
}
