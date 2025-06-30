package com.template.springproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  // Habilita WebSockets con STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws") // Punto de conexión WebSocket
      //.setAllowedOrigins("*")  // Permite conexiones desde cualquier origen
      .setAllowedOriginPatterns("*");
      //.withSockJS();  // Habilita soporte para navegadores que no tienen WebSocket nativo
  }
  
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic"); // Prefijo para mensajes enviados a los clientes
    registry.setApplicationDestinationPrefixes("/app"); // Prefijo para mensajes enviados al servidor
  }
}