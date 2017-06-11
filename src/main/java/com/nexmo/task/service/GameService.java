package com.nexmo.task.service;


import com.nexmo.task.domain.AppUser;
import com.nexmo.task.domain.Game;
import com.nexmo.task.exceptions.ExceptionCode;
import com.nexmo.task.exceptions.SystemException;
import com.nexmo.task.repository.GameRepository;
import com.nexmo.task.repository.UserRepository;
import com.nexmo.task.util.HangmanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    private static final String[] PLAYABLE_CHARACTERS = {
      "ANGELINA JOLIE",
      "BRAD PITT",
      "JENNIFER ANISTON",
      "DONALD KNUTH",
      "ERLICH BACHMAN"
    };
    private static final Integer HEALTH_REDUCER = 20;
    private static final Integer STARTING_HEALTH = 100;
    private static final String REPLACED_LETTER = "_";

    public Game createGame(Long id) throws SystemException {
        AppUser appUser = userRepository.findOne(id);

        if(appUser == null){
          throw new SystemException(ExceptionCode.USER_NOT_FOUND);
        }

        Game game = new Game(appUser, STARTING_HEALTH);
        String character = PLAYABLE_CHARACTERS[
          ThreadLocalRandom.current().nextInt(0, PLAYABLE_CHARACTERS.length)
        ];
        game.setCharacter(character);
        game.setState(character.replaceAll("[A-Z]", REPLACED_LETTER));
        return this.gameRepository.save(game);
    }

    public String guessLetter(Long gameId, String letter){
        Game game = gameRepository.findOne(gameId);
        if(game == null) {
            throw new SystemException(ExceptionCode.GAME_NOT_FOUND);
        }

        if(Game.DEFAULT_ALPHABET.indexOf(letter) < 0){
            throw new SystemException(ExceptionCode.LETTER_NOT_ALPHABETICAL);
        }
        else if(game.getState().indexOf(letter) >= 0
                || game.getAlphabet().indexOf(letter) < 0){
            return "Already used this letter";
        }
        else if(game.getCharacter().indexOf(letter) >= 0){
            game.setState(
                HangmanUtil.getState(
                    letter.toCharArray()[0],
                    game.getCharacter(),
                    game.getState()
                )
            );
            game.setAlphabet(game.getAlphabet().replace(letter,""));
            if(game.getCharacter().equals(game.getState())){
                game.setFinished(true);
                gameRepository.save(game);
                return "Congrats you found the word! It was "+game.getCharacter()+"! Game over! :)";
            }
            else {
                gameRepository.save(game);
                return game.getState() +
                        "\nAvailable characters: " +
                        game.getAlphabet();
            }
        }
        else{
            game.setHealth(game.getHealth() - HEALTH_REDUCER);
            game.setAlphabet(game.getAlphabet().replace(letter, ""));
            if(game.getHealth().equals(0)){
                game.setFinished(true);
                gameRepository.save(game);
                return "Game over! :(";
            }
            else{
                gameRepository.save(game);
                // TODO: Use a string formatter here
                return "Woops. Wrong Letter. Health: " +
                        game.getHealth() +
                        "\n" +
                        game.getState() +
                        "\nAvailable characters: " +
                        game.getAlphabet();
            }
        }
    }

    public List<Game> findAllGames(){
        final List<Game> resultList = new ArrayList<>();
        final Iterable<Game> all = this.gameRepository.findAll();
        all.forEach(resultList::add);
        return resultList;
    }

}
