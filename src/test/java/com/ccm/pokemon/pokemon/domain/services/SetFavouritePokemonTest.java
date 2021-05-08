package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.infrastructure.repositories.InMemoryPokemonRepository;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.inject.Inject;

import static org.mockito.Mockito.when;

@QuarkusTest
public class SetFavouritePokemonTest {
    @Inject
    SetFavouritePokemon tested;

    @Test
    public void verify_setFavouritePokemon_create_callsToMethods() throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        PokemonId pokemonId = new PokemonId(1);
        Pokemon pokemon = new Pokemon(new Name("bulbasaur"), new PokemonId(1));

        PokemonRepository pokemonRepository = Mockito.mock(InMemoryPokemonRepository.class);
        when(pokemonRepository.exists(pokemonId)).thenReturn(false);
        QuarkusMock.installMockForType(pokemonRepository, PokemonRepository.class);

        tested.setFavouritePokemon(pokemonId);
        Mockito.verify(pokemonRepository, Mockito.times(0)).find(pokemonId);
        Mockito.verify(pokemonRepository, Mockito.times(0)).updateFavouriteCounter(pokemon);
        Mockito.verify(pokemonRepository, Mockito.times(1)).create(pokemonId);
    }

    /*@Test
    public void verify_setFavouritePokemon_update_callsToMethods() throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        PokemonId pokemonId = new PokemonId(1);
        Pokemon pokemon = new Pokemon(new Name("bulbasaur"), new PokemonId(1));

        PokemonRepository pokemonRepository = Mockito.mock(InMemoryPokemonRepository.class);
        when(pokemonRepository.exists(pokemonId)).thenReturn(true);
        QuarkusMock.installMockForType(pokemonRepository, PokemonRepository.class);

        tested.setFavouritePokemon(pokemonId);
        Mockito.verify(pokemonRepository, Mockito.times(1)).find(pokemonId);
        Mockito.verify(pokemonRepository, Mockito.times(1)).updateFavouriteCounter(pokemon);
        Mockito.verify(pokemonRepository, Mockito.times(0)).create(pokemonId);
    }*/
}
