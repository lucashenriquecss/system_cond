package com.example.system_cond.dto;

import com.example.system_cond.entity.Resident;
import com.example.system_cond.entity.Unit;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDTO extends Unit {
    private String id;
    private Integer number;
    private Integer roomNumber;
    private List<Resident> residents;

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<com.example.system_cond.entity.Resident> residents) {
        this.residents = residents;
    }
}
