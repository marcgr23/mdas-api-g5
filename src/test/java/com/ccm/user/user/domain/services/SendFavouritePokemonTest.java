package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.interfaces.EventSender;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.infrastructure.eventSender.RabbitEventSender;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
public class SendFavouritePokemonTest {
    @Inject
    SendFavouritePokemon tested;

    @Test
    public void verify_setFavouritePokemon_callsToMethods() throws Exception {
        FavouritePokemonId favouritePokemonId = new FavouritePokemonId(1);

        EventSender eventSender = Mockito.mock(RabbitEventSender.class);
        QuarkusMock.installMockForType(eventSender, EventSender.class);

        tested.execute(favouritePokemonId);
        Mockito.verify(eventSender, Mockito.times(1)).sendMessageToQueue(favouritePokemonId);
    }
}
