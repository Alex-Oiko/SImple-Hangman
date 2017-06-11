package com.nexmo.task.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class AppUser {

  @Id
  private String username;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Game> games;

  public AppUser(){}

  public AppUser(String username){
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Game> getGames() {
    return games;
  }

  public void setGames(List<Game> games) {
    this.games = games;
  }
}
