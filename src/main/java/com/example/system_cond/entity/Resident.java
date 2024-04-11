package com.example.system_cond.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
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

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<Reserve> reserves;

    @JsonIgnore // Ignorar a propriedade unit durante a serialização JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="unit_id")
    private Unit unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }


}
