package com.ccm.pokemon.pokemon.infrastructure.eventReceiver;

import com.ccm.pokemon.pokemon.application.useCases.SetFavouritePokemonUseCase;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.rabbitmq.client.*;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class RabbitEventReceiver {

    @Inject
    SetFavouritePokemonUseCase setFavouritePokemonUseCase;

    public void onStart(@Observes StartupEvent start) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("favourite-pokemon", true, false, false, null);

        DeliverCallback deliverCallback = this::processMessage;
        channel.basicConsume("favourite-pokemon", true, deliverCallback, consumerTag -> {
        });
    }

    public void processMessage(String consumerTag, Delivery delivery) {
        try {
            String message = new String(delivery.getBody(), "UTF-8");
            PokemonId pokemonId = new PokemonId(Integer.parseInt(message));

            setFavouritePokemonUseCase.SetFavouritePokemonByPokemonId(pokemonId);

        } catch (UnknownException | NetworkConnectionException | com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException | PokemonNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
