package com.example.system_cond.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "balance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer balanceAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(Integer balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }
    public void registerSpent(Integer valueSpent) {
        balanceAvailable -= valueSpent;
    }

    public void registerPayment(Integer valuePayment) {
        balanceAvailable += valuePayment;
    }
}
