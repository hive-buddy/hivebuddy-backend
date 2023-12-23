package be.kdg.team9.hivebuddy.controllers;

import be.kdg.team9.hivebuddy.data.SensorData;
import be.kdg.team9.hivebuddy.data.TextMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebSocketTextController {

    SimpMessagingTemplate template;

    @Autowired
    public WebSocketTextController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody List<String> textMessages) {
        List<TextMessageDTO> textMessageDTOs = textMessages.stream()
                .map(message -> {
                    TextMessageDTO dto = new TextMessageDTO();
                    dto.setMessage(message);
                    return dto;
                })
                .collect(Collectors.toList());

        template.convertAndSend("/topic/message", textMessageDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
        // receive message from client
    }


    @SendTo("/overview/{hiveId}")
//    public String[][] broadcastMessage(@Payload String[][] realtimeData) {
//        return realtimeData;
//    }
    public SensorData broadcastMessage(@Payload SensorData sensorData) {
        return sensorData;
    }

    //    @SendTo("/topic/message")
//    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
//        return textMessageDTO;
//    }
    @SendTo("/topic/message")
    public List<TextMessageDTO> broadcastMessage(@Payload List<TextMessageDTO> textMessageDTOs) {
        return textMessageDTOs;
    }
}