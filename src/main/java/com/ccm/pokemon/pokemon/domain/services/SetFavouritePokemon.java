package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class SetFavouritePokemon {

    @Inject
    @Named("InMemoryPokemon")
    PokemonRepository pokemonRepository;

    public void setFavouritePokemon(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        if(pokemonRepository.exists(pokemonId)){
            pokemonRepository.updateFavouriteCounter(pokemonRepository.find(pokemonId));
        }else{
            pokemonRepository.create(pokemonId);
        }
    }
}
