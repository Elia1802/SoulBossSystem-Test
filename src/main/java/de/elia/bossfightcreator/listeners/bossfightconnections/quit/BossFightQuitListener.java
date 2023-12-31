package de.elia.bossfightcreator.listeners.bossfightconnections.quit;

import de.elia.api.events.bossfight.connections.quit.BossFightQuitEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.jetbrains.annotations.NotNull;

import static de.elia.api.messages.builder.MessageBuilder.gold;
import static de.elia.api.messages.builder.MessageBuilder.gray;

//This event called if a player quit a party
public class BossFightQuitListener implements Listener {


  @EventHandler
  private void onQuit(@NotNull BossFightQuitEvent event){
    event.sendQuitMessage(
      gold(event.getQuitedPlayer().getName()).append(gray(" hat das Game verlassen!"))
    );
  }

}
