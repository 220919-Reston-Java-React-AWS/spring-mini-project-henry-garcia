package com.example.demo.service;

import com.example.demo.model.Pokemon;
import com.example.demo.model.Trainers;
import com.example.demo.repository.PokeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private TrainerRepository trainerRepository;

    private PokeRepository pokeRepository;

    public TrainerService(TrainerRepository trainerRepository, PokeRepository pokeRepository) {
        this.trainerRepository = trainerRepository;
        this.pokeRepository = pokeRepository;
    }

    public Trainers add(Trainers trainer) {return this.trainerRepository.save(trainer);}

    public Pokemon addPoke(Trainers trainer, int numId){
        Pokemon pokemon = pokeRepository.getReferenceById(numId);
        pokemon.setTrainer(trainer);
        return  this.pokeRepository.save(pokemon);
    }

    public List<Pokemon> getByTrainer(Trainers trainer){ return this.pokeRepository.findByTrainer(trainer);}
}
