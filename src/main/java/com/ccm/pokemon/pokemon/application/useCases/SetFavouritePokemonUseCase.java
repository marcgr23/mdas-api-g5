package com.ccm.pokemon.pokemon.application.useCases;

import com.ccm.pokemon.pokemon.application.dto.PokemonDto;
import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.services.PokemonFinder;
import com.ccm.pokemon.pokemon.domain.services.SetFavouritePokemon;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SetFavouritePokemonUseCase {
    @Inject
    SetFavouritePokemon setFavouritePokemon;

    public void SetFavouritePokemonByPokemonId (PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        setFavouritePokemon.setFavouritePokemon(pokemonId);
    }
}
