package com.example.goguessmultiplayer;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.time.Instant;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

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
}