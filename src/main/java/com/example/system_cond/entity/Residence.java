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
    private Number number;
    @Column(length = 10,unique = true)
    private Number roomNumber;

    @OneToMany(mappedBy = "residence", cascade = CascadeType.ALL)
    private List<Resident> residents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Number getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Number roomNumber) {
        this.roomNumber = roomNumber;
    }
}
