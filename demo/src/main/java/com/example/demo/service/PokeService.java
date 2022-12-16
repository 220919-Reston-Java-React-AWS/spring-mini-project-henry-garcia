package com.example.demo.service;

import com.example.demo.model.Pokemon;
import com.example.demo.repository.PokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokeService {

    private PokeRepository pokeRepository;

    public PokeService(PokeRepository pokeRepository){
        this.pokeRepository = pokeRepository;
    }

    public Pokemon upsertPoke(Pokemon pokemon){
        return this.pokeRepository.save(pokemon);
    }

    public List<Pokemon> getAllPokes(){
        return this.pokeRepository.findAll();
    }

    public Optional<Pokemon> findById(int numId){
        return pokeRepository.findById(numId);
    }

    public Pokemon getById(int numId){
        return this.pokeRepository.getReferenceById(numId);
    }

    public Pokemon updateLevel(Pokemon poke) {
        Pokemon pokemon = pokeRepository.getReferenceById(poke.getNumId());
        pokemon.setLevel(poke.getLevel());
        return this.pokeRepository.save(pokemon);
    }
}
