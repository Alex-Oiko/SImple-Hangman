package com.nexmo.task.controller;


import com.fasterxml.jackson.databind.node.TextNode;
import com.nexmo.task.domain.Game;
import com.nexmo.task.response.StringResponse;
import com.nexmo.task.service.GameService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping(value = "/create/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> createGame(@PathVariable String username) {
        return ResponseEntity.ok().body(gameService.createGame(username));
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
