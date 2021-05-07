package com.ccm.user.user.infrastructure.eventSender;

import com.ccm.user.user.domain.interfaces.EventSender;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.SenderProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
@Named("RabbitSender")
public class RabbitEventSender implements EventSender {

    private Connection connection;
    private Channel channel;
    private ConnectionFactory connectionFactory;
    private SenderProperties senderProperties;

    public void onStart(@Observes StartupEvent start){
        setEnvironment(new SenderProperties(
                "localhost",
                5672,
                "favourite-pokemon"
        ));
    }

    public void setEnvironment(SenderProperties senderProperties) {
        try {
            this.senderProperties = senderProperties;
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(senderProperties.getHost());
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(
                    senderProperties.getQueueName(),
                    true,
                    false,
                    false,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageToQueue(FavouritePokemonId pokemonId) throws Exception {
        channel.basicPublish(
                "",
                senderProperties.getQueueName(),
                null,
                String.valueOf(pokemonId.getPokemonId()).getBytes(StandardCharsets.UTF_8)
        );
    }
}
