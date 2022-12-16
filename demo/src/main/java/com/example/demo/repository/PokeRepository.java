package com.example.demo.repository;

import com.example.demo.model.Pokemon;
import com.example.demo.model.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokeRepository extends JpaRepository<Pokemon, Integer> {

    List<Pokemon> findByTrainer(Trainers trainer);
}
