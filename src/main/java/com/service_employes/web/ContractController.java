package com.service_employes.web;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.service_employes.dtos.ContractDTO;
import com.service_employes.services.ContractService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@CrossOrigin(origins = "*")
public class ContractController {


    private ContractService contractService;

    private final Path rootLocation = Paths.get("contracts");

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping(value = "/{employeId}")
    public ContractDTO addContract(@PathVariable Long employeId,
                                   @RequestBody ContractDTO contractDTO) throws IOException {
        ContractDTO savedContract = contractService.addContract(employeId,contractDTO);
        return savedContract;
    }

    @PostMapping(value = "/{contractId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ContractDTO addContract(@PathVariable Long contractId,
                                   @RequestPart("file") MultipartFile file) throws IOException, IOException {
        return contractService.uploadContract(contractId,file);
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        List<ContractDTO> contracts = contractService.getAllContracts();
        return (contracts);
    }
}
