package com.example.system_cond.controller;

import com.example.system_cond.dto.ExtractDTO;
import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.entity.Extract;
import com.example.system_cond.entity.Payment;
import com.example.system_cond.service.ExtractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/extract")
public class ExtractController {
    private final ExtractService extractService;

    public ExtractController(ExtractService extractService){
        this.extractService = extractService;
    }
    @GetMapping
    public ResponseEntity<List<ExtractDTO>> getAllExtract() {
        List<ExtractDTO> extract = extractService.getAllExtract().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());;
        return ResponseEntity.ok(extract);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExtractDTO> getExtractById(@PathVariable String id) {
        ExtractDTO extract = extractService.getExtractById(id);
        return ResponseEntity.ok(extract);
    }

    private ExtractDTO convertToDTO(Extract extract) {
        ExtractDTO extractDTO = new ExtractDTO();
        extractDTO.setId(extract.getId());
        extractDTO.setValue(extract.getValue());
        extractDTO.setDateTransaction(extract.getDateTransaction());
        extractDTO.setTypeTransaction(extract.getTypeTransaction());
        extractDTO.setDetails(extract.getDetails());
        extractDTO.setDescription(extract.getDescription());
        return extractDTO;
    }


}
