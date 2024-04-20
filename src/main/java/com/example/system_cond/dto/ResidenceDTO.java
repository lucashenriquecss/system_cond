package com.example.system_cond.dto;
import com.example.system_cond.entity.Residence;
import com.example.system_cond.entity.Resident;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceDTO extends Residence {

    private Long id;



    private Integer number;

    private Integer roomNumber;
    private List<Resident> residents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }
}
