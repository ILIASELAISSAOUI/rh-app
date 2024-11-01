package com.service_employes.web;

import com.service_employes.dtos.EmployeDTO;
import com.service_employes.entities.Employe;
import com.service_employes.services.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeController {
    private EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public Page<EmployeDTO> getEmployes(@RequestParam int page, @RequestParam int size){
        return employeService.getEmployes(page, size);
    }

    @GetMapping("/{demandeCongeId}")
    public EmployeDTO getEmploye(@PathVariable long demandeCongeId){
        return employeService.getEmployeByDemandeConge(demandeCongeId);
    }

    @GetMapping("/{id}/employe")
    public EmployeDTO getEmployeById(@PathVariable long id){
        return employeService.getEmployeById(id);
    }

    @GetMapping("/search")
    public Page<EmployeDTO> searchEmployes(@RequestParam String motCle, @RequestParam int page, @RequestParam int size) {
        return employeService.searchEmployes(motCle, page, size);
    }

    @GetMapping("/user")
    public EmployeDTO findEmployeByEmail(@RequestParam String email){
        return employeService.getEmployeByEmail(email);
    }

    @PostMapping
    public EmployeDTO createEmployee(@RequestBody EmployeDTO employe) {
        return employeService.addEmploye(employe);
    }

    @PutMapping("/{id}/upgradeEmploye")
    public EmployeDTO updateEmployee(@PathVariable long id, @RequestBody EmployeDTO employeDTO) {
        return employeService.upgradeEmploye(id,employeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
         employeService.deleteEmploye(id);
    }


}
