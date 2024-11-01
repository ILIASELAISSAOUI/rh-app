package com.service_employes.web;

import com.service_employes.dtos.DepartementDTO;
import com.service_employes.entities.Departement;
import com.service_employes.services.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departement")
@CrossOrigin(origins = "*")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping
    public DepartementDTO addDepartement(@RequestBody DepartementDTO departement) {
        return departementService.addDepartement(departement);
    }

    @GetMapping
    public List<DepartementDTO> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable long id) {
        departementService.deleteDepartement(id);
    }
}