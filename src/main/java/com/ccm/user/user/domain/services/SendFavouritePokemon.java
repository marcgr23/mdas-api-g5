package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.interfaces.EventSender;
import com.ccm.user.user.domain.vo.FavouritePokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class SendFavouritePokemon {

    @Inject
    @Named("RabbitSender")
    EventSender eventSender;

    public void execute(FavouritePokemonId pokemonId) throws Exception {
        eventSender.sendMessageToQueue(pokemonId);
    }
}
