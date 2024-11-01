package com.service_employes.services;

import com.service_employes.dtos.DemandeTeletravailDTO;
import com.service_employes.dtos.EmployeDTO;
import com.service_employes.dtos.JourDTO;
import com.service_employes.entities.DemandeTeletravail;
import com.service_employes.entities.Employe;
import com.service_employes.entities.JourTeletravail;
import com.service_employes.mapper.Mapper;
import com.service_employes.outil.EtatDemande;
import com.service_employes.repos.DemandeTeletravailRepository;
import com.service_employes.repos.JourRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DemandeTeletravailServiceImplt implements DemandeTeletravailService{

    private Mapper mapper;
    private DemandeTeletravailRepository demandeTeletravailRepository;
    private EmployeService employeService;
    private JourRepository jourRepository;

    public DemandeTeletravailServiceImplt(DemandeTeletravailRepository demandeTeletravailRepository,
                                          JourRepository JourRepository,
                                          EmployeService employeService,
                                          Mapper mapper) {
        this.demandeTeletravailRepository=demandeTeletravailRepository;
        this.jourRepository=JourRepository;
        this.employeService=employeService;
        this.mapper=mapper;
    }

    @Override
    public DemandeTeletravailDTO createDemandeTeletravail(Long employeId,DemandeTeletravailDTO demandeTeletravailDTO) {
        LocalDate today=LocalDate.now();
        DemandeTeletravail demandeTeletravail=mapper.fromDemandeTeletravailDTO(demandeTeletravailDTO);
        EmployeDTO employeDTO=employeService.getEmployeById(employeId);
        demandeTeletravail.setDateDemande(today);
        demandeTeletravail.setEmploye(mapper.fromEmployeDTO(employeDTO));
        demandeTeletravailRepository.save(demandeTeletravail);
        return mapper.fromDemandeTeletravail(demandeTeletravail);
    }

    @Override
    public boolean updateEtatDemandeTeletravail(Long demandeId) {
        DemandeTeletravail demandeTeletravail=demandeTeletravailRepository.findById(demandeId).orElse(null);
        if (demandeTeletravail!=null) {
            demandeTeletravail.setTraite(true);
            demandeTeletravailRepository.save(demandeTeletravail);
            return true;
        }
        return false;
    }


    public DemandeTeletravailDTO addJoursToDemandeTeletravail(Long demandeId, List<JourDTO> jours) {
        DemandeTeletravail demandeTeletravail = demandeTeletravailRepository.findById(demandeId).orElse(null);

        if (demandeTeletravail != null) {
            List<JourTeletravail> jourTeletravails = jours.stream()
                    .map(jourDTO -> {
                        JourTeletravail jourTeletravail = mapper.fromJourDTO(jourDTO);
                        jourTeletravail.setEtatDemande(EtatDemande.ENATTENTE);
                        jourTeletravail.setDemandeTeletravail(demandeTeletravail);
                        return jourTeletravail;
                    })
                    .collect(Collectors.toList());

            demandeTeletravail.getJourTeletravails().addAll(jourTeletravails);
            demandeTeletravailRepository.save(demandeTeletravail);
            return mapper.fromDemandeTeletravail(demandeTeletravail);
        } else {
            return null;
        }
    }



//    @Override
//    public JourDTO addJour(Long demandeId, JourDTO jourDTO) {
//        DemandeTeletravail demandeTeletravail=demandeTeletravailRepository.findById(demandeId).orElse(null);
//        JourTeletravail jourTeletravail1=mapper.fromJourDTO(jourDTO);
//        jourTeletravail1.setDemandeTeletravail(demandeTeletravail);
//        jourTeletravail1.setEtatDemande(EtatDemande.ENATTENTE);
//        jourRepository.save(jourTeletravail1);
//        return mapper.fromJour(jourTeletravail1);
//    }

    @Override
    public List<DemandeTeletravailDTO> getAllDemandeTeletravail() {
        List<DemandeTeletravail> demandeTeletravails=demandeTeletravailRepository.findAll();
        List<DemandeTeletravailDTO>demandeTeletravailDTO=demandeTeletravails.stream().
                map(demandeTeletravail -> mapper.fromDemandeTeletravail(demandeTeletravail)).
                collect(Collectors.toList());
        return demandeTeletravailDTO;
    }

    @Override
    public List<JourDTO> getJoursByDemandeTeletravails(Long demandeId) {
        List<JourTeletravail> jours = jourRepository.findAllByDemandeTeletravailId(demandeId);
        if (jours == null) {
            return new ArrayList<>();
        }
        return jours.stream()
                .map(jour -> mapper.fromJour(jour))
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeTeletravailDTO> getDemandeTeletravailsByEmploye(Long employeId) {
        List<DemandeTeletravail> demandes=demandeTeletravailRepository.findAllByEmployeId(employeId);
        List<DemandeTeletravailDTO> demandesDtos=demandes.stream().
                map(demande-> mapper.fromDemandeTeletravail(demande)).
                collect(Collectors.toList());
        return demandesDtos;
    }

    @Override
    public boolean approuveJour(Long idJour) {
        JourTeletravail jourTeletravail = jourRepository.findById(idJour).orElse(null);
        if (jourTeletravail != null) {
            jourTeletravail.setEtatDemande(EtatDemande.APPROUVE);
            jourRepository.save(jourTeletravail);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectJour(Long idJour, String motif) {
        JourTeletravail jourTeletravail = jourRepository.findById(idJour).orElse(null);
        if (jourTeletravail != null) {
            jourTeletravail.setEtatDemande(EtatDemande.REJETE);
            jourTeletravail.setMotif(motif);
            jourRepository.save(jourTeletravail);
            return true;
        }
        return false;
    }

    @Override
    public void approuveAll(Long idDemandeTeletravail) {
        DemandeTeletravail demandeTeletravail=demandeTeletravailRepository.findById(idDemandeTeletravail).orElse(null);
        demandeTeletravail.getJourTeletravails().forEach(jourTeletravail -> {
            jourTeletravail.setEtatDemande(EtatDemande.APPROUVE);
        });
        demandeTeletravailRepository.save(demandeTeletravail);
    }

    @Override
    public void rejectAll(Long idDemandeTeletravail) {
        DemandeTeletravail demandeTeletravail=demandeTeletravailRepository.findById(idDemandeTeletravail).orElse(null);
        demandeTeletravail.getJourTeletravails().forEach(jourTeletravail -> {
            jourTeletravail.setEtatDemande(EtatDemande.REJETE);
        });
        demandeTeletravailRepository.save(demandeTeletravail);
    }


    @Override
    @Transactional
    public void rejectExpiredDemandes() {
        LocalDate today = LocalDate.now().minusDays(1);
        List<JourTeletravail> expiredDemandes = jourRepository.findAllByEtatDemandeAndDate(EtatDemande.ENATTENTE, today);
        for (JourTeletravail demande : expiredDemandes) {
            demande.setEtatDemande(EtatDemande.REJETE);
        }
        jourRepository.saveAll(expiredDemandes);
    }
}
