package com.example.system_cond.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "residents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String contact;
    private String email;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<Payment> payments;
    @ManyToOne
    @JoinColumn(name ="unit_id")
    private Unit unit;
}
