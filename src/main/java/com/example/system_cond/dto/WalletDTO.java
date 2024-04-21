package com.example.system_cond.dto;
import com.example.system_cond.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO extends Wallet {
    private Long id;
    private BigDecimal wallet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWallet() {
        return wallet;
    }


    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

}
