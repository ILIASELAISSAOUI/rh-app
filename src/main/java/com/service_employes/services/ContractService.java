package com.service_employes.services;

import com.service_employes.dtos.ContractDTO;
import com.service_employes.entities.Contract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContractService {
    public ContractDTO addContract(Long employeId,ContractDTO contractDTO) throws IOException;
    public ContractDTO uploadContract(Long contractID,MultipartFile file) throws IOException;
    public List<ContractDTO> getAllContracts();
}
