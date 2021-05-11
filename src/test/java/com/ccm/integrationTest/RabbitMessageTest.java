package com.ccm.integrationTest;

import com.ccm.pokemon.pokemon.domain.services.SetFavouritePokemon;
import com.ccm.pokemon.pokemon.infrastructure.eventReceiver.RabbitEventReceiver;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.infrastructure.eventSender.RabbitEventSender;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class RabbitMessageTest {
    @Inject
    RabbitEventSender testedSender;

    @Inject
    RabbitEventReceiver testedReceiver;

    @Test
    public void verify_sendMessage() throws Exception {

        SetFavouritePokemon setFavouritePokemon = Mockito.mock(SetFavouritePokemon.class);
        Mockito.doNothing().when(setFavouritePokemon).setFavouritePokemon(any());
        QuarkusMock.installMockForType(setFavouritePokemon, SetFavouritePokemon.class);

        testedSender.sendMessageToQueue(new FavouritePokemonId(1));

        Thread.sleep(500);

        verify(setFavouritePokemon, times(1)).setFavouritePokemon(any());
    }
}
