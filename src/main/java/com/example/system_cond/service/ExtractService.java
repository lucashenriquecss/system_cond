package com.example.system_cond.service;

import com.example.system_cond.repository.ExtractRepository;
import org.springframework.stereotype.Service;

@Service
public class ExtractService {
    private final ExtractRepository extractRepository;

    public ExtractService(ExtractRepository extractRepository){
        this.extractRepository = extractRepository;
    }
}
