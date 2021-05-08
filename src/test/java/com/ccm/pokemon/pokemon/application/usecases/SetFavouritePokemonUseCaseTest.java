package com.ccm.pokemon.pokemon.application.usecases;

import com.ccm.pokemon.pokemon.application.useCases.SetFavouritePokemonUseCase;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.services.SetFavouritePokemon;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class SetFavouritePokemonUseCaseTest {
    @Inject
    SetFavouritePokemonUseCase tested;

    @Test
    public void verify_setFavouritePokemon_CallsToMethods() throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        PokemonId pokemonId = new PokemonId(1);

        SetFavouritePokemon setFavouritePokemon = Mockito.mock(SetFavouritePokemon.class);
        Mockito.doNothing().when(setFavouritePokemon).setFavouritePokemon(pokemonId);
        QuarkusMock.installMockForType(setFavouritePokemon, SetFavouritePokemon.class);

        tested.SetFavouritePokemonByPokemonId(pokemonId);

        verify(setFavouritePokemon, times(1)).setFavouritePokemon(any());
    }
}
