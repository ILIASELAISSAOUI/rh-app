package com.service_employes.dtos;

import com.service_employes.outil.StatusEmploye;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmployeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private StatusEmploye statusEmploye;
    private DepartementDTO departement;
    private ContractDTO contract;
}
