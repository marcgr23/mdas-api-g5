package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserFavouritePokemonDTO;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.services.AddFavouritePokemon;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@QuarkusTest
public class AddFavouritePokemonUseCaseTest {
    @Inject
    AddFavouritePokemonUseCase tested;

    @Test
    public void verify_addFavouritePokemon_CallsToMethods() throws Exception {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
            pokemonId.getPokemonId(),
            userId.getUserId()
        );

        AddFavouritePokemon addFavouritePokemon = Mockito.mock(AddFavouritePokemon.class);
        Mockito.doNothing().when(addFavouritePokemon).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemon, AddFavouritePokemon.class);

        tested.addFavouritePokemon(userFavouritePokemonDTO);

        Mockito.verify(addFavouritePokemon, times(1)).execute(pokemonId, userId);
    }

    @Test
    public void verify_addFavouritePokemon_throwsUserNotFoundException () throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
                pokemonId.getPokemonId(),
                userId.getUserId()
        );

        AddFavouritePokemon addFavouritePokemon = Mockito.mock(AddFavouritePokemon.class);
        Mockito.doThrow(UserNotFoundException.class).when(addFavouritePokemon).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemon, AddFavouritePokemon.class);

        assertThrows(UserNotFoundException.class, () -> {
            tested.addFavouritePokemon(userFavouritePokemonDTO);
        });
    }

    @Test
    public void verify_addFavouritePokemon_throwsFavouritePokemonAlreadyExistsException () throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
                pokemonId.getPokemonId(),
                userId.getUserId()
        );

        AddFavouritePokemon addFavouritePokemon = Mockito.mock(AddFavouritePokemon.class);
        Mockito.doThrow(FavouritePokemonAlreadyExistsException.class).when(addFavouritePokemon).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemon, AddFavouritePokemon.class);

        assertThrows(FavouritePokemonAlreadyExistsException.class, () -> {
            tested.addFavouritePokemon(userFavouritePokemonDTO);
        });
    }
}
