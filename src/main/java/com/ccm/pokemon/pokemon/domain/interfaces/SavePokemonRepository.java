package com.ccm.pokemon.pokemon.domain.interfaces;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;


public interface SavePokemonRepository {

    public Pokemon find(PokemonId pokemonId);

    public boolean exists(PokemonId pokemonId);

    public void create(Pokemon pokemon);

    public Pokemon update(Pokemon pokemon);
}
