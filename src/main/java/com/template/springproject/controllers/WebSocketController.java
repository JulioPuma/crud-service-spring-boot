package com.template.springproject.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    
    // Recibe mensajes desde "/app/changeStatus" y los envía a "/topic/status"
    @MessageMapping("/changeStatus")
    @SendTo("/topic/status")
    public String sendStatusUpdate(String status) {
        return "Nuevo estado: " + status;
    }
}