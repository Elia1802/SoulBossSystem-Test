package de.elia.achivementssystem;

import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;

import de.elia.Main;
import de.elia.systemclasses.Instances;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

//The Main of the Achievement Plugin
public class AchievementMain implements SoulPlugin {

  private static AchievementMain achievementMain;

  //Load the Achievement Plugin
  @Override
  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.achievementPluginLogger().logInfo("Load Achievement plugin...");
    achievementMain = this;
    this.achievementPluginLogger().logInfo("Achievement plugin loaded!");
  }

  //Unload the Achievement Plugin.
  @Override
  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.achievementPluginLogger().logInfo("Achievement disabled!");
  }

  //Gets this class.
  @NotNull
  public static AchievementMain achievementMain() {
    return achievementMain;
  }

  //Gets the Logger of the Achievement plugin.
  @NotNull
  public PluginLogger achievementPluginLogger() {
    return Instances.ACHIEVEMENT_LOGGER;
  }

  //Gets the Main class of this system
  @NotNull
  public Main main() {
    return Main.main();
  }
}
