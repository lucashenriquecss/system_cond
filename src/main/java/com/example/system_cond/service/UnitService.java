package com.example.system_cond.service;

import com.example.system_cond.entity.Unit;
import com.example.system_cond.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Unit getUnitById(String id) {
        return unitRepository.findById(id).orElse(null);
    }

    public Unit createUnit(Unit unit) {
        // Adicione qualquer lógica de validação ou processamento aqui, se necessário
        return unitRepository.save(unit);
    }

    public Unit updateUnit(String id, Unit updatedUnit) {
        Unit unit = unitRepository.findById(id).orElse(null);
        if (unit != null) {
            // Atualize os campos da unidade existente com os valores da unidade atualizada
            // Certifique-se de que a entidade de unidade passada está corretamente configurada com o ID atualizado, se necessário
            // Adicione qualquer outra lógica de validação ou processamento aqui, se necessário
            return unitRepository.save(updatedUnit);
        }
        return null; // ou lance uma exceção indicando que a unidade não foi encontrada
    }

    public void deleteUnit(String id) {
        unitRepository.deleteById(id);
    }
}
