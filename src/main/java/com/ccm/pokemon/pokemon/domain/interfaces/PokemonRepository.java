package com.ccm.pokemon.pokemon.domain.interfaces;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;


public interface PokemonRepository {

    public Pokemon find(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException;

    public boolean exists(PokemonId pokemonId);

    public void updateFavouriteCounter(Pokemon pokemon);

    public void create(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException;
}
