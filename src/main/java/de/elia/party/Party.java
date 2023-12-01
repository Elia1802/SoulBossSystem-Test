package de.elia.party;

import de.elia.api.events.bossfight.connections.join.BossFightJoinEvent;
import de.elia.api.events.bossfight.connections.quit.BossFightQuitEvent;

import de.elia.Main;
import de.elia.party.utils.PartyUtils;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static de.elia.api.messages.builder.MessageBuilder.aqua;
import static de.elia.api.messages.builder.MessageBuilder.gray;
import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;

//This class create a Party for the games
public class Party {

  private final Player owner;
  private int id;
  private final Set<Player> members;
  private int playerAmount = 3;

  public Party(Player owner, int id){
    this.owner = owner;
    this.id = id;
    this.members = new HashSet<>();
    this.owner.getPersistentDataContainer().set(new NamespacedKey(Main.main(), Integer.toString(id)), PersistentDataType.INTEGER, id);
    this.members.add(owner);
    PartyUtils.PARTYS.put(owner, this);
    message(owner, gray("Die Party ").append(aqua(Integer.toString(id))).append(aqua(" wurde erfolgreich erstellt!")));
    message(owner, gray("Du kannst mit dem Befehl").append(aqua(" /party add ")).append(aqua(Integer.toString(id)).append(aqua(" [PLAYER] ")).append(gray("ein neuen Spieler hinzufügen!"))));
    message(owner, gray("Und mit dem Befehl").append(aqua(" /party remove ")).append(aqua(Integer.toString(id))).append(aqua(" [PLAYER] ")).append(gray("kannst du ein Spieler aus der Party löschen!")));
  }

  //This methode add a player to the party
  public void addPlayer(@NotNull Player newMember){
    if (this.members.contains(newMember)) {
      message(
        this.owner,
        red("Dieser Spieler ist schon in der Party!")
      );
      return;
    }else {
      if (this.playerAmount == 0) {
        message(
          this.owner,
          red("Die maximale Anzahl an Spielern einer Party ist erreicht!")
        );
        return;
      }else {
        this.members.add(newMember);
        this.playerAmount--;
        BossFightJoinEvent event = new BossFightJoinEvent(this.members, newMember);
        Bukkit.getServer().getPluginManager().callEvent(event);
        return;
      }
    }
  }

  //This methode remove a player of the party
  public void removePlayer(@NotNull Player target) {
    if (!this.members.contains(target)) {
      message(
        this.owner,
        red("Dieser Spieler ist nicht in der Party!")
      );
      return;
    }else {
      members.remove(target);
      this.playerAmount++;
      BossFightQuitEvent event = new BossFightQuitEvent(this.members, target);
      Bukkit.getServer().getPluginManager().callEvent(event);
      return;
    }
  }

  //This methode remove the party owner
  public void removeParty(@NotNull Player owner){
    this.members.clear();
    PartyUtils.PARTYS.remove(owner);
    owner.getPersistentDataContainer().remove(new NamespacedKey(Main.main(), Integer.toString(id)));
  }

  //Gets the list with all members
  @NotNull
  public Set<Player> members(){
    return this.members;
  }

  //Gets the Id
  public int id(){
    return this.id;
  }

  //Gets the owner of the party
  @NotNull
  public Player owner(){
    return this.owner;
  }
}
