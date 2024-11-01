package com.service_employes.services;

import com.service_employes.dtos.ContractDTO;
import com.service_employes.dtos.EmployeDTO;
import com.service_employes.entities.Contract;
import com.service_employes.entities.Employe;
import com.service_employes.mapper.Mapper;
import com.service_employes.repos.ContractRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContractServiceImplt implements ContractService {
    private ContractRepository contractRepository;
    private EmployeService employeService;
    private Mapper mapper;

    private final Path rootLocation = Paths.get("contracts");

    ContractServiceImplt(ContractRepository contractRepository,
                         EmployeService employeService,
                         Mapper mapper) {
        this.mapper=mapper;
        this.employeService=employeService;
        this.contractRepository = contractRepository;
    }

    @Override
    public ContractDTO addContract(Long employeId,ContractDTO contractDTO) throws IOException {
            EmployeDTO employeDTO=employeService.getEmployeById(employeId);
            Employe employe=mapper.fromEmployeDTO(employeDTO);
            Contract contract=mapper.fromContractDTO(contractDTO);
            contract.setEmploye(employe);
            contractRepository.save(contract);
            return mapper.fromContract(contract);
    }

    @Override
    public ContractDTO uploadContract(Long contractID, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            Contract contract=contractRepository.findById(contractID).get();
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = generateUniqueFileName(originalFileName);
            Path destinationFile = rootLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), destinationFile);
            contract.setUrlFile(uniqueFileName);
            contractRepository.save(contract);
            return mapper.fromContract(contract);
        }
        return null;
    }



    @Override
    public List<ContractDTO> getAllContracts() {
        List<Contract> contracts=contractRepository.findAll();
        List<ContractDTO> contractDTOS=contracts.stream().
                map(contract -> mapper.fromContract(contract)).
                collect(Collectors.toList());
        return contractDTOS;
    }

    private String generateUniqueFileName(String originalFileName) {
        String[] parts = originalFileName.split("\\.");
        String name = parts[0];
        String extension = parts.length > 1 ? "." + parts[1] : "";
        String uniqueSuffix = String.valueOf(new Random().nextInt(10000));
        return name + "_" + uniqueSuffix + extension;
    }
}
