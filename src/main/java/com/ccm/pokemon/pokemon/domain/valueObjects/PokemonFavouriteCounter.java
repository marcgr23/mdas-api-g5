package com.ccm.pokemon.pokemon.domain.valueObjects;

import java.util.ArrayList;
import java.util.List;

public class PokemonFavouriteCounter {

    private int pokemonFavouriteCounter;

    public PokemonFavouriteCounter() {
        this.pokemonFavouriteCounter = 1;
    }

    public int getPokemonFavouriteCounter() { return pokemonFavouriteCounter; }

    public void increaseCounter() {
        pokemonFavouriteCounter++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonFavouriteCounter that = (PokemonFavouriteCounter) o;
        return pokemonFavouriteCounter == that.pokemonFavouriteCounter;
    }
}
