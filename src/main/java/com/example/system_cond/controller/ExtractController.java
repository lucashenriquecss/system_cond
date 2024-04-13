package com.example.system_cond.controller;

import com.example.system_cond.service.ExtractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/extract")
public class ExtractController {
    private final ExtractService extractService;

    public ExtractController(ExtractService extractService){
        this.extractService = extractService;
    }
}
