package com.example.goguessmultiplayer.service;

import com.example.goguessmultiplayer.entity.GameRoom;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {

    private final Map<UUID, GameRoom> roomMap = new HashMap<>();

    public UUID createGameRoom(){
        //create Room, save to db, return room uuid
        GameRoom gameRoom = new GameRoom();
        gameRoom.setUuid(UUID.randomUUID());
        roomMap.put(gameRoom.getUuid(), gameRoom);
        return gameRoom.getUuid();
    }

    public GameRoom findGameRoom(UUID uuid){
        return roomMap.get(uuid);
    }
}
