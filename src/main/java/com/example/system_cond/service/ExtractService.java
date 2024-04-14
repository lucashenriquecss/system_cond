package com.example.system_cond.service;

import com.example.system_cond.dto.ExtractDTO;
import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.dto.SpentDTO;
import com.example.system_cond.entity.Extract;
import com.example.system_cond.entity.Payment;
import com.example.system_cond.repository.ExtractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtractService {
    private final ExtractRepository extractRepository;

    public ExtractService(ExtractRepository extractRepository){
        this.extractRepository = extractRepository;
    }



    public List<ExtractDTO> getAllExtract() {
        return extractRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public ExtractDTO createExtractPayment(PaymentDTO paymentDTO){

        ExtractDTO extractDTO = new ExtractDTO();
        extractDTO.setDescription("Pagamento mensal de condominio");
        extractDTO.setDetails(paymentDTO.getResidentId());
        extractDTO.setValue(paymentDTO.getValue());
        extractDTO.setDateTransaction("data hoje"); //TODO:mudar o tipo depois
        extractDTO.setTypeTransaction("payment");

        Extract extract = convertToEntity(extractDTO);
        Extract savedExtract = extractRepository.save(extract);
        return convertToDTO(savedExtract);
    }
    public ExtractDTO createExtractSpent(SpentDTO spentDTO){

        ExtractDTO extractDTO = new ExtractDTO();
        extractDTO.setDescription(spentDTO.getDescription());
        extractDTO.setDetails(spentDTO.getType());
        extractDTO.setValue(spentDTO.getValue());
        extractDTO.setDateTransaction("data hoje"); //TODO:mudar o tipo depois
        extractDTO.setTypeTransaction("spent");

        Extract extract = convertToEntity(extractDTO);
        Extract savedExtract = extractRepository.save(extract);
        return convertToDTO(savedExtract);
    }
    public ExtractDTO getExtractById(String id) {
        Optional<Extract> extractOptional = extractRepository.findById(id);
        return extractOptional.map(this::convertToDTO).orElse(null);
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

    private Extract convertToEntity(ExtractDTO extractDTO) {
        Extract extract = new Extract();

        extract.setValue(extractDTO.getValue());
        extract.setDateTransaction(extractDTO.getDateTransaction());
        extract.setDescription(extractDTO.getDescription());
        extract.setDetails(extractDTO.getDetails());
        extract.setTypeTransaction(extractDTO.getTypeTransaction());

        return extract;
    }
}
