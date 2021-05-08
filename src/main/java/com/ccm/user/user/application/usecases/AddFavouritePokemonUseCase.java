package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserFavouritePokemonDTO;
import com.ccm.user.user.domain.services.AddFavouritePokemon;
import com.ccm.user.user.domain.services.SendFavouritePokemon;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddFavouritePokemonUseCase {

    @Inject
    AddFavouritePokemon addFavouritePokemon;

    @Inject
    SendFavouritePokemon sendFavouritePokemon;

    public void addFavouritePokemon (UserFavouritePokemonDTO user) throws Exception {
        FavouritePokemonId _pokemonId = new FavouritePokemonId(user.getPokemonId());
        UserId _userId = new UserId(user.getUserId());

        addFavouritePokemon.execute(_pokemonId, _userId);
        sendFavouritePokemon.execute(_pokemonId);
    }
}
