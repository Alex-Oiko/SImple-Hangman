package com.nexmo.task;


import com.nexmo.task.domain.AppUser;
import com.nexmo.task.domain.Game;
import com.nexmo.task.repository.GameRepository;
import com.nexmo.task.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo(UserRepository userRepository, GameRepository gameRepository) {
    return (args) -> {
      AppUser appUser= new AppUser((long)1,"Alex");
      userRepository.save(appUser);
      Game game = new Game(appUser, 100);
      String character = "ERLICH BACHMAN";
      game.setCharacter(character);
      game.setState(character.replaceAll("[A-Z]", "_"));
      gameRepository.save(game);
    };

  }

}
