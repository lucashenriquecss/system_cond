package com.example.system_cond.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "residences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 10,unique = true)
    private Integer number;
    @Column(length = 10,unique = true)
    private Integer roomNumber;

    @OneToMany(mappedBy = "residence", cascade = CascadeType.ALL)
    private List<Resident> residents;

    public Long getId() {
        return id;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
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
}
