package com.example.goguessmultiplayer;

import com.example.goguessmultiplayer.entity.GameRoom;
import com.example.goguessmultiplayer.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    GameService gameService;

    //expose a STOMP endpoint

    /*
    /portfolio is the HTTP URL for the endpoint to which a WebSocket
    (or SockJS) client needs to connect for the WebSocket handshake.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/portfolio");
    }

    /*
    STOMP messages whose destination header begins with /app
    are routed to @MessageMapping methods in @Controller classes.

    Use the built-in message broker for subscriptions and broadcasting
    and route messages whose destination header begins with /topic or /queue to the broker.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/queue");
    }

    @Controller
    public class GreetingController {
        @MessageMapping("/greeting")
        public String handle(String greeting) {
            return "[" + Instant.now() + ": " + greeting;
        }
    }

    @Controller
    public class TestController {
        @MessageMapping("/room/{uuid}")
        public String handle(@DestinationVariable("uuid") UUID uuid, String message) {

            // по айди нашли игру из бд
            // в зависимости от комманды что-то делать дальше:
            // 1. присоединиться к комнате {слинковать играка с игрой}
            // 2. дать ответ
            GameRoom gameRoom = gameService.findGameRoom(uuid);

            switch (message) {
                case "join": {
                    System.out.println("player x joined");
                    //countOfPlayers;
                }
                case "ready": {
                    System.out.println("player x joined");
                    //countOfPlayers; = countOfReady
                }
                case "choose": {
                    System.out.println("player x made choose");
                }
            }

            return "[" + message + ": uuid: " + uuid;
        }
    }
}
