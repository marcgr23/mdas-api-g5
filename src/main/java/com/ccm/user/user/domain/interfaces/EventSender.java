package com.ccm.user.user.domain.interfaces;

import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.SenderProperties;

public interface EventSender {

    public void sendMessageToQueue(FavouritePokemonId pokemonId) throws Exception;
}
