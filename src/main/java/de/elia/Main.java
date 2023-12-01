package de.elia;

import de.elia.api.Initialize;
import de.elia.api.loader.exceptions.SoulPluginLoadException;

import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.world.WorldMain;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static de.elia.systemclasses.Instances.ACHIEVEMENT_MAIN;
import static de.elia.systemclasses.Instances.BOSS_FIGHT_CREATOR;
import static de.elia.systemclasses.Instances.ITEM_MAIN;
import static de.elia.systemclasses.Instances.MAIN_LOGGER;
import static de.elia.systemclasses.Instances.PARTY_MAIN;
import static de.elia.systemclasses.Instances.SOUL_BOSS;
import static de.elia.systemclasses.Instances.SOUL_BOSS_SYSTEM;
import static com.sk89q.worldedit.WorldEdit.getInstance;
import static de.elia.api.logging.error.SaveError.saveError;
import static de.elia.systemclasses.register.commands.CommandRegister.registerCommands;
import static de.elia.systemclasses.register.events.ListenerRegister.registerListener;
import static org.bukkit.Bukkit.getPluginManager;

//This class load the complete system. Its the main class of all things in this system
public class Main extends JavaPlugin {

  public static Main main;
  private static WorldCreator worldCreator;
  public static World world;
  private WorldMain worldMain;
  public static boolean isWorldEditReady;

  //Load this system
  public void onEnable() {
    main = this;

    MAIN_LOGGER.logInfo("Checks if SoulLibrary loaded...");
    if (getPluginManager().getPlugin("SoulLibrary") == null) {
      MAIN_LOGGER.logError("SoulLibrary is not loaded!");
      try {
        throw new NullPointerException("SoulLibrary is null!");
      } catch (NullPointerException exception) {
        MAIN_LOGGER.logError("An error occurred while loading SoulLibrary! (See Stacktrace)");
        saveError(this, exception, "Main-onEnable-line_47=null");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
        return;
      }
    }
    Initialize.init(getPluginManager(),this);
    MAIN_LOGGER.logInfo("SoulLibrary is loaded!");

    MAIN_LOGGER.logInfo("Try to load Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem...");

    try {
      MAIN_LOGGER.logInfo("Load Achievements plugin...");
      ACHIEVEMENT_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Achievements plugin loaded!");
    } catch (SoulPluginLoadException exception) {
      MAIN_LOGGER.logError("An error occurred while loading Achievements plugin! (See Stacktrace)");
      saveError(this, exception, "Main-onEnable-line_67=soulpluginload");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    try {
      MAIN_LOGGER.logInfo("Load Item...");
      ITEM_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Item loaded!");
    } catch (SoulPluginLoadException exception) {
      MAIN_LOGGER.logError("An error occurred while loading Item plugin! (See Stacktrace)");
      saveError(this, exception, "Main-onEnable-line_80=soulpluginload");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    try {
      MAIN_LOGGER.logInfo("Load BossFightCreator...");
      BOSS_FIGHT_CREATOR.enable(this);
      MAIN_LOGGER.logInfo("BossFightCreator loaded!");
    } catch (SoulPluginLoadException exception) {
      MAIN_LOGGER.logError("An error occurred while loading BossFightCreator plugin! (See Stacktrace)");
      saveError(this, exception, "Main-onEnable-line_93=soulpluginload");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    try {
      MAIN_LOGGER.logInfo("Load SoulBoss...");
      SOUL_BOSS.enable(this);
      MAIN_LOGGER.logInfo("SoulBoss loaded!");
    } catch (SoulPluginLoadException exception) {
      saveError(this, exception, "Main-onEnable-line_106=soulpluginload");
      MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    try {
      MAIN_LOGGER.logInfo("Load SoulBoss...");
      SOUL_BOSS_SYSTEM.enable(this);
      MAIN_LOGGER.logInfo("SoulBoss loaded!");
    } catch (SoulPluginLoadException exception) {
      saveError(this, exception, "Main-onEnable-line_119=soulpluginload");
      MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    try {
      MAIN_LOGGER.logInfo("Unload Party...");
      PARTY_MAIN.disable(this);
      MAIN_LOGGER.logInfo("Party unloaded!");
    }catch (SoulPluginLoadException exception) {
      saveError(this, exception, "Main-onEnable-line_132=soulpluginload");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    }

    MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem loaded!");
    try {
      registerCommands();
    } catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Commands! (See Stacktrace)");
      saveError(this, exception, "Main-onEnable-line_143=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    try {
      registerListener();
    } catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Events (See Stacktrace)");
      saveError(this, exception, "Main-onEnable-line_154=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }

    if (getInstance().getPlatformManager().isInitialized()) {
      isWorldEditReady = true;
    }else isWorldEditReady = false;


    if (isWorldEditReady){
      try {
        this.worldMain = new WorldMain(this);
        this.generateWorld("world_bossfight");
        ArenaHandler.init(this);
      } catch (Exception exception) {
        MAIN_LOGGER.logError("An error occurred while loading the world and the arenas (See Stacktrace)");
        saveError(this, exception, "Main-onEnable-line_172-173=exception");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
        return;
      }
    }else {
      MAIN_LOGGER.logError("Worldedit is not used because it's not ready!");
      MAIN_LOGGER.logWarning("---------------------------------------------------------------------------------------------");
      MAIN_LOGGER.logWarning("Worldedit is not ready!");
      MAIN_LOGGER.logWarning("The Arenas and the world of " + this.getName() + " are not loaded because of this problem.");
      MAIN_LOGGER.logWarning("Please check your Worldedit!");
      MAIN_LOGGER.logWarning("---------------------------------------------------------------------------------------------");
    }

    MAIN_LOGGER.logInfo("Loaded de.elia.Main");
  }

  //Unload this system
  public void onDisable() {
    MAIN_LOGGER.logInfo("Stop de.elia.Main");

    MAIN_LOGGER.logInfo("Checks if " + getPluginManager().getPlugin("SoulLibrary").getName() + " and " + getPluginManager().getPlugin("WorldEdit").getName() + " loaded...");
    if (getPluginManager().getPlugin("SoulLibrary") != null || getPluginManager().getPlugin("WorldEdit") != null) {
      MAIN_LOGGER.logInfo(getPluginManager().getPlugin("SoulLibrary").getName() + " and " + getPluginManager().getPlugin("WorldEdit").getName() + "is loaded!");
      MAIN_LOGGER.logInfo("Try to unload Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem...");

      try {
        MAIN_LOGGER.logInfo("Unload achievement plugin...");
        ACHIEVEMENT_MAIN.disable(this);
        MAIN_LOGGER.logInfo("Achievement plugin unloaded!");
      } catch (SoulPluginLoadException exception) {
        saveError(this, exception, "Main-onDisable-line_205=soulpluginload");
        MAIN_LOGGER.logError("An error occurred while disabling achievement plugin! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }

      try {
        MAIN_LOGGER.logInfo("Unload BossFightCreator...");
        BOSS_FIGHT_CREATOR.disable(this);
        MAIN_LOGGER.logInfo("BossFightCreator unloaded!");
      } catch (SoulPluginLoadException exception) {
        saveError(this, exception, "Main-onDisable-line_216=soulpluginload");
        MAIN_LOGGER.logError("An error occurred while disabling BossFightCreator! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }

      try {
        MAIN_LOGGER.logInfo("Unload Item...");
        ITEM_MAIN.disable(this);
        MAIN_LOGGER.logInfo("Item unloaded!");
      } catch (SoulPluginLoadException exception) {
        saveError(this, exception, "Main-onDisable-line_227=soulpluginload");
        MAIN_LOGGER.logError("An error occurred while disabling Item! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }

      try {
        MAIN_LOGGER.logInfo("Unload SoulBoss...");
        SOUL_BOSS.disable(this);
        MAIN_LOGGER.logInfo("SoulBoss unloaded!");
      } catch (SoulPluginLoadException exception) {
        saveError(this, exception, "Main-onDisable-line_238=soulpluginload");
        MAIN_LOGGER.logError("An error occurred while disabling SoulBoss! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }

      try {
        MAIN_LOGGER.logInfo("Unload Party...");
        PARTY_MAIN.disable(this);
        MAIN_LOGGER.logInfo("Party unloaded!");
      }catch (SoulPluginLoadException exception) {
        saveError(this, exception, "Main-onEnable-line_249=soulpluginload");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
      }

      try {
        MAIN_LOGGER.logInfo("Unload SoulBossSystem...");
        SOUL_BOSS_SYSTEM.disable(this);
        MAIN_LOGGER.logInfo("SoulBossSystem unloaded!");
      } catch (SoulPluginLoadException exception) {
        saveError(this, exception, "Main-onDisable-line_260=soulpluginload");
        MAIN_LOGGER.logError("An error occurred while disabling SoulBossSystem! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }

      MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem unloaded!");
      MAIN_LOGGER.logInfo("de.elia.Main stopped!");
      return;
    }
    MAIN_LOGGER.logWarning("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem not corect unloaded!");
    MAIN_LOGGER.logError("SoulLibrary and WorldEdit is not loaded!");
    MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
  }

  public void onReload() {
    this.onDisable();
    this.onEnable();
  }

  public void generateWorld(String id) {
    this.worldMain.logInfo("0%");
    this.worldMain.logInfo("Create a new World... (" + id + ")");
    this.worldMain.logInfo("Load org.bukkit.WorldGenerator...");
    worldCreator = new WorldCreator(id);
    this.worldMain.logInfo("org.bukkit.WorldGenerator loaded!");
    this.worldMain.logInfo("25%");
    this.worldMain.logInfo("Load de.elia.bossfightcreator.world.creator.CustomChunkGenerator...");
    CustomChunkGenerator generator = new CustomChunkGenerator();
    this.worldMain.logInfo("de.elia.bossfightcreator.world.creator.CustomChunkGenerator loaded!");
    this.worldMain.logInfo("50%");
    this.worldMain.logInfo("Set the custom generator to the WorldGenerator...");
    worldCreator.generator(generator);
    this.worldMain.logInfo("The custom generator to the WorldGenerator sets!");
    this.worldMain.logInfo("75%");
    this.worldMain.logInfo("Create a new org.bukkit.World...");
    world = Bukkit.createWorld(worldCreator);
    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
    world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
    world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
    world.setGameRule(GameRule.KEEP_INVENTORY, true);
    this.worldMain.logInfo("A new org.bukkit.World is created!");
    this.worldMain.logInfo("Ending world creation progress...");
    this.worldMain.logInfo("100%");
    this.worldMain.logInfo("world creation progress ended!");
  }

  //Gets the world main
  public WorldMain worldMain() {
    return this.worldMain;
  }

  //Gets the main of this system
  @NotNull
  public static Main main() {
    return main;
  }

  //Gets soul library
  @Nullable
  public Plugin soulLibraryMain() {
    return getPluginManager().getPlugin("SoulLibrary");
  }

  //Disable this plugin
  public void disable() {
    getPluginManager().disablePlugin(this);
  }

  //Gets worldedit
  @Nullable
  public Plugin worldEditPlugin() {
    return getPluginManager().getPlugin("WorldEdit");
  }
}
