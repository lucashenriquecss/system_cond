package com.example.system_cond.service;

import com.example.system_cond.entity.Resident;
import com.example.system_cond.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    @Autowired
    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public Resident getResidentById(String id) {
        return residentRepository.findById(id).orElse(null);
    }

    public Resident createResident(Resident resident) {
        // Adicione qualquer lógica de validação ou processamento aqui, se necessário
        return residentRepository.save(resident);
    }

    public Resident updateResident(String id, Resident updatedResident) {
        Resident resident = residentRepository.findById(id).orElse(null);
        if (resident != null) {
            // Atualize os campos do residente existente com os valores do residente atualizado
            // Certifique-se de que a entidade residente passada está corretamente configurada com o ID atualizado, se necessário
            // Adicione qualquer outra lógica de validação ou processamento aqui, se necessário
            return residentRepository.save(updatedResident);
        }
        return null; // ou lance uma exceção indicando que o residente não foi encontrado
    }

    public void deleteResident(String id) {
        residentRepository.deleteById(id);
    }
}
