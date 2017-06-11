package com.nexmo.task.domain;


import javax.persistence.*;

@Entity
@Table
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Boolean finished;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    @Column
    private String character;

    @Column
    private String state;

    @Column
    private String alphabet;

    @Column
    private Integer health;

    private static final String DEFAULT_ALPHABET="ABCDEFGHIJKLMNOPQRSTVUWXYZ";

    public Game(){}

    public Game(appUser, health){
      this.appUser = appUser
      this.finished = false;
      this.health = health;
      this.alphabet = DEFAULT_ALPHABET;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
