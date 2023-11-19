package com.example.goguessmultiplayer;

import com.example.goguessmultiplayer.dto.CreateGameDto;
import com.example.goguessmultiplayer.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class HttpController {

    GameService gameService;
    
    @PostMapping("/createRoom")
    UUID createNewRoom(@RequestBody CreateGameDto createGameDto) {
        return gameService.createGameRoom();
    }
}
