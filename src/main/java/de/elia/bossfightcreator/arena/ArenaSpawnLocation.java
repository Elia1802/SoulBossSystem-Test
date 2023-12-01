package de.elia.bossfightcreator.arena;

import de.elia.api.logging.error.SaveError;

import de.elia.bossfightcreator.BossFightCreatorMain;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.String;
import java.util.ArrayList;

import static de.elia.api.messages.builder.MessageBuilder.gold;
import static de.elia.api.messages.builder.MessageBuilder.gray;
import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;
import static de.elia.api.messages.builder.MessageBuilder.yellow;

//This class gets the spawn location for the arenas
public class ArenaSpawnLocation {

  private static final ArrayList<String> ARENA_IDs = new ArrayList<>();

  static {
    ARENA_IDs.add("arena_1.0");
    ARENA_IDs.add("arena_1.1");
    ARENA_IDs.add("arena_1.2");
    ARENA_IDs.add("arena_1.3");
    ARENA_IDs.add("arena_1.4");
    ARENA_IDs.add("arena_1.5");
    ARENA_IDs.add("arena_1.6");
    ARENA_IDs.add("arena_1.7");
    ARENA_IDs.add("arena_1.8");
    ARENA_IDs.add("arena_1.9");
    ARENA_IDs.add("arena_2.0");
    ARENA_IDs.add("arena_2.1");
    ARENA_IDs.add("arena_2.2");
    ARENA_IDs.add("arena_2.3");
    ARENA_IDs.add("arena_2.4");
    ARENA_IDs.add("arena_2.5");
    ARENA_IDs.add("arena_2.6");
    ARENA_IDs.add("arena_2.7");
    ARENA_IDs.add("arena_2.8");
    ARENA_IDs.add("arena_2.9");
  }

  @Nullable
  public static Location spawnLocation(@NotNull String arenaID, Player player){
    if (ARENA_IDs.contains(arenaID)) {
      switch (arenaID) {
        case "arena_1.0" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 117.0, 21.0, 259.0);
        }
        case "arena_1.1" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 317.0, 21.0, 259.0);
        }
        case "arena_1.2" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 517.0, 21.0, 259.0);
        }
        case "arena_1.3" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 158.0, 21.0, 259.0);
        }
        case "arena_1.4" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 208.0, 21.0, 259.0);
        }
        case "arena_1.5" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 717.0, 21.0, 259.0);
        }
        case "arena_1.6" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 917.0, 21.0, 259.0);
        }
        case "arena_1.7" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1117.0, 21.0, 259.0);
        }
        case "arena_1.8" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1317.0, 21.0, 259.0);
        }
        case "arena_1.9" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1527.0, 21.0, 259.0);
        }
        case "arena_2.0" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 9, 81, 242);
        }
        case "arena_2.1" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 209, 81, 242);
        }
        case "arena_2.2" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 409, 81, 242);
        }
        case "arena_2.3" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 609, 81, 242);
        }
        case "arena_2.4" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 809, 81, 242);
        }
        case "arena_2.5" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1009, 81, 242);
        }
        case "arena_2.6" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1209, 81, 242);
        }
        case "arena_2.7" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1409, 81, 242);
        }
        case "arena_2.8" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1609, 81, 242);
        }
        case "arena_2.9" -> {
          return new Location(Bukkit.getWorld("world_bossfight"), 1809, 81, 242);
        }
      }
      message(player, red("ERROR: FÜR DEINE ARENA GIBT ES ZUR ZEIT KEINE SPAWNLOCATION!"));
      message(player, red("Dies kann an folgenden Dingen liegen:"));
      message(player, yellow("  - Zur Zeit sind keine Arenen verfügbar"));
      message(player, yellow("  - Die ArenaID existiert nicht"));
      message(player, yellow("  - Ein Fehler beim erstellen des Spieles"));
      message(player, yellow("  - Die Zielwelt existiert nicht"));
      message(player, gold("Melde diesen Fehler bitte umgehend in einen Ticket als Bugreport!"));
      message(player, gray("ArenaID: " + arenaID + " has no spawn location -> location = null..."));
      try {
        throw new NullPointerException("ArenaID: " + arenaID + " has no spawn location -> location = null");
      }catch (NullPointerException exception) {
        exception.printStackTrace();
        SaveError.saveError(BossFightCreatorMain.bossFightCreator().main(), exception, "ArenaSpawnLocation-spawnLocation-line_128=null");
      }
      return null;
    }else {
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logError("The arena id " + arenaID + " is null!");
      return null;
    }
  }
}
