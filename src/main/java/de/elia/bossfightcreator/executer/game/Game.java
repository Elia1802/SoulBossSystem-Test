package de.elia.bossfightcreator.executer.game;

import java.util.ArrayList;
import java.util.UUID;

//This interface marked a class to this class is a game
//TODO: Move this class to the Library
public interface Game {

  ArrayList<UUID> OLD_GAME_PLAYERS = new ArrayList<>();
  ArrayList<Game> ALL_ACTIVE_GAMES = new ArrayList<>();

  void kill(String reason, boolean isRestart);

}
