package com.example.system_cond.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDTO {
    private String id;
    private Integer number;
    private Integer roomNumber;
    private List<ResidentDTO> residents;
}
