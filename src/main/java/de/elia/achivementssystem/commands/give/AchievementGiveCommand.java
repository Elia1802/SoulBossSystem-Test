package de.elia.achivementssystem.commands.give;

import de.elia.api.achievements.Achievements;
import de.elia.api.logging.PluginLogger;

import de.elia.achivementssystem.AchievementMain;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.elia.achivementssystem.achievement.Achievement.giveAchievement;
import static de.elia.api.messages.builder.MessageBuilder.aqua;
import static de.elia.api.messages.builder.MessageBuilder.gold;
import static de.elia.api.messages.builder.MessageBuilder.gray;
import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;
import static de.elia.systemclasses.messages.Message.messageWithPrefix;

//TODO: UPDATE ACHIEVEMENTSYSTEM

/**
 * TODO LIST
 *  - Create Inventory to see all achievements
 *  - Create Database to save the achievements for the players
 *  - New Animation for the Achievements
 */

//A Command to give a Player an Achievement
public class AchievementGiveCommand extends Command {

  public AchievementGiveCommand() {
    this("achievementgive", "The achievementgive command give the player a Achievement.", "Use /achievementhelp [PLAYER] [ACHIEVEMENT]", Arrays.asList("achievementg", "agive"));
  }

  public AchievementGiveCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String subCommand, String[] args) {
    PluginLogger logger = AchievementMain.achievementMain().achievementPluginLogger();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.achievement.give")) {
        if (args.length == 2) {
          Player target = Bukkit.getPlayer(args[0]);
          if (args[1].equalsIgnoreCase("soulbosssystem:achievementsystem:BOSSFIGHT")) {
            if (target.getName() == null) {
              message(player, gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            giveAchievement(target, Achievements.BOSSFIGHT);
            messageWithPrefix(target, gray("Du hast vom Teammitglied ").append(aqua(player.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT.getName())).append(gray(" bekommen!")));
            messageWithPrefix(player, gray("Du hast dem Spieler ").append(aqua(target.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT.getName())).append(gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:achievementsystem:BOSSFIGHT_ZOMBIE")) {
            if (target.getName() == null) {
              message(player, gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE);
            messageWithPrefix(target, gray("Du hast vom Teammitglied ").append(aqua(player.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_ZOMBIE.getName())).append(gray(" bekommen!")));
            messageWithPrefix(player, gray("Du hast dem Spieler ").append(aqua(target.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_ZOMBIE.getName())).append(gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_ZOMBIE.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_ZOMBIE_END")) {
            if (target.getName() == null) {
              message(player, gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            giveAchievement(target, Achievements.BOSSFIGHT_ZOMBIE_END);
            messageWithPrefix(target, gray("Du hast vom Teammitglied ").append(aqua(player.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_ZOMBIE_END.getName())).append(gray(" bekommen!")));
            messageWithPrefix(player, gray("Du hast dem Spieler ").append(aqua(target.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_ZOMBIE_END.getName())).append(gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_ZOMBIE_END.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_CREEPER")) {
            if (target.getName() == null) {
              message(player, gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            giveAchievement(target, Achievements.BOSSFIGHT_CREEPER);
            messageWithPrefix(target, gray("Du hast vom Teammitglied ").append(aqua(player.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_CREEPER.getName())).append(gray(" bekommen!")));
            messageWithPrefix(player, gray("Du hast dem Spieler ").append(aqua(target.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_CREEPER.getName())).append(gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_CREEPER.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_CREEPER_END")) {
            if (target.getName() == null) {
              message(player, gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            giveAchievement(target, Achievements.BOSSFIGHT_CREEPER_END);
            messageWithPrefix(target, gray("Du hast vom Teammitglied ").append(aqua(player.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_CREEPER.getName())).append(gray(" bekommen!")));
            messageWithPrefix(player, gray("Du hast dem Spieler ").append(aqua(target.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_CREEPER.getName())).append(gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_CREEPER.getName() + " gegeben");
            return true;
          }
          if (args[1].equalsIgnoreCase("soulbosssystem:plugin:achievement:BOSSFIGHT_CREEPER_MINI")) {
            if (target.getName() == null) {
              message(player, gold("Dieser Spieler existiert nicht oder ist nicht online!"));
              return false;
            }
            giveAchievement(target, Achievements.BOSSFIGHT_CREEPER_MINI);
            messageWithPrefix(target, gray("Du hast vom Teammitglied ").append(aqua(player.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_CREEPER_MINI.getName())).append(gray(" bekommen!")));
            messageWithPrefix(player, gray("Du hast dem Spieler ").append(aqua(target.getName())).append(gray(" den Erfolg ")).append(aqua(Achievements.BOSSFIGHT_CREEPER_MINI.getName())).append(gray(" gegeben!")));
            logger.logInfo("Das Teammitglied " + player.getName() + " hat den Spieler " + target.getName() + " den Erfolg " + Achievements.BOSSFIGHT_CREEPER_MINI.getName() + " gegeben");
            return true;
          }
          message(player, red("Das Achievement ").append(aqua(args[1])).append(red(" gibt es nicht")));
          return false;
        }
        message(player, red("/achievementgive [PLAYER] [ACHIEVEMENT]"));
        return false;
      }
      message(player, red("Du hast keine Rechte für diesen Command"));
      return false;
    }
    message(sender, red("You have to be a Player!"));
    return false;
  }

  @Override
  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String subCommand, String[] args) {
    ArrayList<String> tab1 = new ArrayList<>();
    ArrayList<String> tab2 = new ArrayList<>();
    if (args.length == 1) {
      Bukkit.getOnlinePlayers().forEach(player -> tab1.add(player.getName()));
      return tab1;
    }
    if (args.length == 2) {
      tab2.add("soulbosssystem:achievementsystem:BOSSFIGHT");
      tab2.add("soulbosssystem:achievementsystem:BOSSFIGHT_ZOMBIE");
      tab2.add("soulbosssystem:achievementsystem:BOSSFIGHT_ZOMBIE_END");
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT_CREEPER");
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT_CREEPER_END");
      tab2.add("soulbosssystem:plugin:achievement:BOSSFIGHT_CREEPER_MINI");
      return tab2;
    }
    return null;
  }
}
