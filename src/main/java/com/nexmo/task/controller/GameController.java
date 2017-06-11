package com.nexmo.task.controller;


import com.nexmo.task.domain.Game;
import com.nexmo.task.response.StringResponse;
import com.nexmo.task.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping(value = "/create/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> createGame(@PathVariable Long userId) {
        return ResponseEntity.ok().body(gameService.createGame(userId));
    }

    @PostMapping(value = "/guessLetter/{gameId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponse> guessLetter(@PathVariable Long gameId, @RequestBody String letter){
        return ResponseEntity.ok().body(new StringResponse(gameService.guessLetter(gameId,letter)));
    }

    @GetMapping(value = "/managementPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Game>> findAllGames() {
        return ResponseEntity.ok().body(gameService.findAllGames());
    }

}
