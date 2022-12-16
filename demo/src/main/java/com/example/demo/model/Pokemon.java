package com.example.demo.model;


//import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int level;

    private String image;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    private Trainers trainer;
}
