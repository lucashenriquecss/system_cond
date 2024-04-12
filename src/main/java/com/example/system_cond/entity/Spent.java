package com.example.system_cond.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;

@Entity
@Table(name = "spent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Spent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private Integer value;
    private String date;
    private String type;
    private String balanceId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getBalanceId() {
        return balanceId;
    }
    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }
}