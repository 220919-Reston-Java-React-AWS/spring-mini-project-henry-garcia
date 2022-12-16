package com.example.demo.controller;

import com.example.demo.model.Pokemon;
import com.example.demo.model.Trainers;
import com.example.demo.repository.PokeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    private final TrainerRepository trainerRepository;

    private final PokeRepository pokeRepository;

    public TrainerController(TrainerService trainerService, TrainerRepository trainerRepository, PokeRepository pokeRepository){
        this.trainerService = trainerService;
        this.trainerRepository = trainerRepository;
        this.pokeRepository = pokeRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Trainers> addTrainer(@RequestBody Trainers trainer){
        return ResponseEntity.ok(this.trainerService.add(trainer));
    }

    @PatchMapping("/{trainerId}/addPoke/{numId}")
    public ResponseEntity<Pokemon> addPokemon(@PathVariable int trainerId, @PathVariable int numId){
        Optional<Trainers> opttrainer = trainerRepository.findById(trainerId);
        Optional<Pokemon> optpokemon = pokeRepository.findById(numId);
        if(opttrainer.isEmpty() || optpokemon.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        else {
            Trainers trainer = trainerRepository.getReferenceById(trainerId);
            return ResponseEntity.ok(this.trainerService.addPoke(trainer,numId));
        }
    }

    @GetMapping("/{trainerId}")
    public ResponseEntity<List<Pokemon>> getPokesByTrainer(@PathVariable int trainerId){
        Optional<Trainers> opt = trainerRepository.findById(trainerId);
        if (opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        else {
            Trainers trainer = trainerRepository.getReferenceById(trainerId);
            return ResponseEntity.ok(this.trainerService.getByTrainer(trainer));
        }
    }
}
