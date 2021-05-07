package com.ccm.pokemon.pokemon.infrastructure.repositories;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.infrastructure.services.PokemonApiService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@ApplicationScoped
@Named("InMemoryPokemon")
public class InMemoryPokemonRepository implements PokemonRepository {

    List<Pokemon> inMemoryPokemons = new ArrayList<>();

    @Inject
    PokemonApiService pokemonApiService;

    @Override
    public Pokemon find(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        return inMemoryPokemons.stream()
                .filter(x -> x.getPokemonId().equals(pokemonId)).findAny()
                .orElse(pokemonApiService.find(pokemonId));
    }

    @Override
    public boolean exists(PokemonId pokemonId) {
        return inMemoryPokemons.stream()
                .anyMatch(x -> x.getPokemonId().equals(pokemonId));
    }

    @Override
    public void updateFavouriteCounter(Pokemon pokemon) {
        pokemon.getPokemonFavouriteTimes().increaseCounter();
        OptionalInt index = IntStream.range(0, inMemoryPokemons.size())
                .filter(i -> pokemon.getPokemonId().equals(inMemoryPokemons.get(i).getPokemonId()))
                .findFirst();

        inMemoryPokemons.set(index.getAsInt(), pokemon);
    }

    @Override
    public void create(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        inMemoryPokemons.add(pokemonApiService.find(pokemonId));
    }
}
