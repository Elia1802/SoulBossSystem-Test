package de.elia.systemclasses.messages;

import de.elia.systemclasses.messages.prefix.Prefix;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import static de.elia.systemclasses.messages.prefix.Prefix.prefix;

//This class has message methodes with the prefix
public class Message {
  private static final MiniMessage miniMessage = MiniMessage.miniMessage();
  private static final Prefix prefix = new Prefix();

  public static void broadcastWithPrefix(String message) {
    Bukkit.broadcast((Component) miniMessage.deserialize((prefix() + message)));
  }

  public static void broadcastWithPrefix(Component message) {
    Bukkit.broadcast(prefix().append(message));
  }

  public static void messageWithPrefix(@NotNull Player player, String message) {
    player.sendMessage(miniMessage.deserialize((prefix() + message)));
  }

  public static void messageWithPrefix(@NotNull Player player, Component message) {
    player.sendMessage(prefix().append(message));
  }

  public static void messageWithPrefix(@NotNull CommandSender sender, String message) {
    sender.sendMessage(miniMessage.deserialize((prefix() + message)));
  }
}
