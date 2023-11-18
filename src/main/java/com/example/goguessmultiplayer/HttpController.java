package com.example.goguessmultiplayer;

import com.example.goguessmultiplayer.dto.CreateGameDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HttpController {

    @PostMapping("/createRoom")
    UUID createNewRoom(@RequestBody CreateGameDto createGameDto) {
        //create Room, save to db, return room uuid
        return UUID.randomUUID();
    }
}
