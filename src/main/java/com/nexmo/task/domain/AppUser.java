package com.nexmo.task.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**A simple user entity, storing users and their usernames
 *
 */
@Entity
public class AppUser {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String username;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser", fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Game> games;

  public AppUser(){}

  public AppUser(Long id,String username){
    this.id = id;
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<Game> getGames() {
    return games;
  }

  public void setGames(Set<Game> games) {
    this.games = games;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
