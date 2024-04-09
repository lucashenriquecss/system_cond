package com.example.system_cond.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "units")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer number;
    private Integer room_number;


    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<Resident> residents;
}
