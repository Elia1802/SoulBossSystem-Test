package de.elia.party.commands;

import de.elia.party.Party;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static de.elia.api.messages.builder.MessageBuilder.aqua;
import static de.elia.api.messages.builder.MessageBuilder.gray;
import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;
import static de.elia.party.utils.PartyUtils.PARTYS;

//This command add/remove a player in a party
public class PartyCommand extends Command {

  protected PartyCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  public PartyCommand(){
    this("party", "Thats the Party command to add or remove a Player", "party add/remove [PARTY] [PLAYER]", List.of("p"));
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if (sender instanceof Player player){
      if (args.length == 3) {
        if (args[0].equalsIgnoreCase("add")) {
          String party = args[1];
          int partyID = Integer.parseInt(party);
          Player target = Bukkit.getPlayer(args[2]);
          if (target == null) {
            message(player, red("Dieser Spieler ist nicht da!"));
            return false;
          }
          if (PARTYS.containsKey(player)) {
            Party p = PARTYS.get(player);
            if (partyID == p.id()) {
              p.addPlayer(target);
              message(target, aqua("Du wurdest der Party ").append(aqua(Integer.toString(p.id()))).append(gray(" eingewiesen!")));
              message(player, aqua("Du hast ").append(aqua(target.getName())).append(gray(" zu deiner Party eingewiesen!")));
              return true;
            }else {
              message(player, red("Dieses Party gibts nicht!"));
              return false;
            }
          }else {
            message(player, red("Du hast keine Party!"));
            return false;
          }
        }else if (args[0].equalsIgnoreCase("remove")) {
          String party = args[2];
          int partyID = Integer.parseInt(party);
          Player target = Bukkit.getPlayer(args[3]);
          if (target == null) {
            message(player, red("Dieser Spieler ist nicht da!"));
            return false;
          }
          if (PARTYS.containsKey(player)) {
            Party p = PARTYS.get(player);
            if (partyID == p.id()) {
              p.removePlayer(target);
              message(target, aqua("Du wurdest von der Party ").append(aqua(Integer.toString(p.id()))).append(gray(" entfernt!")));
              message(player, aqua("Du hast ").append(aqua(target.getName())).append(gray(" aus deiner Party entfernt!")));
              return true;
            }else {
              message(player, red("Dieses Party gibts nicht!"));
              return false;
            }
          }else {
            message(player, red("Du hast kein Party!"));
            return false;
          }
        }else {
          message(player, red(usageMessage));
          return false;
        }
      }else {
        message(player, red("Dieser Command existiert nicht!"));
        return false;
      }
    }else {
      message(sender, red("You have to be a Player!"));
      return false;
    }
  }

  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    List<String> tab1 = new ArrayList<>();
    List<String> tab2 = new ArrayList<>();
    if (args.length == 1) {
      tab1.add("add");
      tab1.add("remove");
      return tab1;
    }else if (args.length == 3) {
      Bukkit.getOnlinePlayers().forEach(player -> tab2.add(player.getName()));
      return tab2;
    }else return null;
  }
}
