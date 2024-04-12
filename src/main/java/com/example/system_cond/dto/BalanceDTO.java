package com.example.system_cond.dto;

import com.example.system_cond.entity.Balance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDTO extends Balance {
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


}
