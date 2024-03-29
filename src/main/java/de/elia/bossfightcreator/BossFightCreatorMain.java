package de.elia.bossfightcreator;

import de.elia.api.game.Game;
import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;

import de.elia.Main;
import de.elia.systemclasses.Instances;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

//This is the main class for the bossfightcreator plugin.
public class BossFightCreatorMain implements SoulPlugin {

  private static BossFightCreatorMain bossFightCreatorMain;
  private static final Map<Player, Integer> playerStatusMap = new HashMap<>();

  //This methode loads this plugin.
  @Override
  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.bossFightCreatorLogger().logInfo("Load BossFightCreator plugin...");
    bossFightCreatorMain = this;
    this.bossFightCreatorLogger().logInfo("BossFightCreator plugin loaded!");
  }

  //This methode unloads this plugin.
  @Override
  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.bossFightCreatorLogger().logInfo("Disable BossFightCreator...");
    Game.ALL_ACTIVE_GAMES.forEach(game -> game.kill("Das Plugin wird beendet!", true));
    this.bossFightCreatorLogger().logInfo("BossFightCreator disabled!");
  }

  //Gets the status map.
  @NotNull
  public static Map<Player, Integer> playerStatusMap() {
    return playerStatusMap;
  }

  //Gets this class.
  public static BossFightCreatorMain bossFightCreator() {
    return bossFightCreatorMain;
  }

  //Gets the instance of the main class of this system.
  @NotNull
  public Main main() {
    return Main.main();
  }

  //Gets the logger for this plugin.
  @NotNull
  public PluginLogger bossFightCreatorLogger() {
    return Instances.BOSS_FIGHT_CREATOR_LOGGER;
  }
}
