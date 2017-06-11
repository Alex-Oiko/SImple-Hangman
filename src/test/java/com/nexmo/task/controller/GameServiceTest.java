package com.nexmo.task.controller;


import com.nexmo.task.domain.AppUser;
import com.nexmo.task.domain.Game;
import com.nexmo.task.repository.GameRepository;
import com.nexmo.task.response.StringResponse;
import com.nexmo.task.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**In order to fully unit test the application and the business logic, I would write more tests involving all of the
 * control statement in the service. That way I would have fully test coverage when testing methods. I would do the same
 * for all of the services of the application in order to be sure that when changes are introduced the critical part of
 * the application remains sound and complete.
 *
 * Once the units tests are finished I would continue to integration tests involving connections to the database and doing
 * actual rest calls.
 *
 */
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Before
    public void settUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuessLetterGameOver() throws Exception {
        AppUser appUser = new AppUser("test");
        Game game = new Game(appUser, 20);
        String character = "ERLICH BACHMAN";
        game.setCharacter(character);
        game.setState(character.replaceAll("[A-Z]", "_"));
        when(gameRepository.findOne((long)1)).thenReturn(game);
        when(gameRepository.save(game)).thenReturn(game);

        assertEquals(gameService.guessLetter((long)1,"P"),"Game over! :(");

    }

    @Test
    public void testGuessLetterGameOverSuccessfully() throws Exception {
        AppUser appUser = new AppUser("test");
        Game game = new Game(appUser, 100);
        String character = "ERLICH BACHMAN";
        game.setCharacter(character);
        game.setState("ERLICH BACHMA_");
        when(gameRepository.findOne((long)1)).thenReturn(game);
        when(gameRepository.save(game)).thenReturn(game);

        assertEquals(gameService.guessLetter((long)1,"N"),"Congrats you found the word! It was ERLICH BACHMAN! Game over! :)");

    }
}
