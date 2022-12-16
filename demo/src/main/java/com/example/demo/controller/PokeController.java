package com.example.demo.controller;

import com.example.demo.model.Pokemon;
import com.example.demo.repository.PokeRepository;
import com.example.demo.service.PokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PokeController {
    private final PokeService pokeService;

    private final PokeRepository pokeRepository;

    public PokeController(PokeService pokeService, PokeRepository pokeRepository){
        this.pokeService = pokeService;
        this.pokeRepository = pokeRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Pokemon> addPoke(@RequestBody Pokemon pokemon){
        return ResponseEntity.ok(this.pokeService.upsertPoke(pokemon));
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAllPokes(){
        return ResponseEntity.ok(this.pokeService.getAllPokes());
    }

    @GetMapping("/{numId}")
    public ResponseEntity<Pokemon> findById(@PathVariable int numId){
        Optional<Pokemon> poke = pokeService.findById(numId);
        if(poke.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(this.pokeService.getById(numId));
        }

    }

    @PatchMapping("/updatelv/{numId}")
    public ResponseEntity<Pokemon> updateLevel(@RequestBody Pokemon pokemon, @PathVariable int numId){

        Optional<Pokemon> opt = pokeService.findById(numId);

        if (opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        else {
            pokemon.setNumId(numId);
            return ResponseEntity.ok(this.pokeService.updateLevel(pokemon));
        }
    }

    @DeleteMapping("/{numId}")
    void deletePoke(@PathVariable int numId){
        this.pokeRepository.deleteById(numId);
    }
}
