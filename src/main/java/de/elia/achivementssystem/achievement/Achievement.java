package de.elia.achivementssystem.achievement;

import de.elia.api.achievements.Achievements;
import de.elia.api.events.achievement.AchievementGiveEvent;

import de.elia.achivementssystem.AchievementMain;
import de.elia.systemclasses.plugin.configuation.SoulBossSystemConfigurationLoader;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import static de.elia.api.messages.builder.MessageBuilder.aqua;
import static de.elia.api.messages.builder.MessageBuilder.gray;
import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;
import static de.elia.systemclasses.messages.Message.broadcastWithPrefix;


//TODO: UPDATE ACHIEVEMENTSYSTEM

/**
 * TODO LIST
 *  - Create Inventory to see all achievements
 *  - Create Database to save the achievements for the players
 *  - New Animation for the Achievements
 */

//This class give and remove the Player an Achievement.
public class Achievement {

  //Requires has the Player an Achievement
  public static boolean hasAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    return SoulBossSystemConfigurationLoader.achievementStorage().get(player.getUniqueId() + ".Achievements." + achievement.dataID()) != null;
  }

  //Give the player an Achievement
  public static void giveAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (!hasAchievement(player, achievement)) {//If player has not the achievement -> give player achievement
      SoulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), true);
      broadcastWithPrefix(aqua(player.getName()).append(gray(" hat den BossFight Erfolg ").append(aqua(achievement.getName()).append(gray(" erreicht")))));
      message(player, gray(achievement.target()));
      player.giveExp(achievement.xp());
      player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
      AchievementGiveEvent event = new AchievementGiveEvent(player, achievement);
      AchievementMain.achievementMain().main().getServer().getPluginManager().callEvent(event);
    } else {//If player has the achievement -> return
      return;
    }
  }

  //Remove the Player an Achievement
  public static void removeAchievement(@NotNull Player player, @NotNull Achievements achievement) {
    if (hasAchievement(player, achievement)) {//If player has the achievement -> give player achievement
      SoulBossSystemConfigurationLoader.achievementStorage().set(player.getUniqueId() + ".Achievements." + achievement.dataID(), null);
      message(player, gray("Du hast dem Spieler den BossFight Erfolg ").append(aqua(achievement.getName() + " (" + achievement.dataID() + ") ").append(gray("abgenommen!"))));
    } else {//If player has not the achievement -> send error message
      message(player, red("Dieser Spieler hat diesen BossFight Erfolg nicht!"));
    }
  }
}

