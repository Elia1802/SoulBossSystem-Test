package de.elia.bossfightcreator.texturepack;

import de.elia.bossfightcreator.BossFightCreatorMain;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class TexturePackPlayerState {

  private static final NamespacedKey KEY = new NamespacedKey(BossFightCreatorMain.bossFightCreator().main(), "resourcepack");

  public static void setState(@NotNull Player player, @NotNull Plugin plugin, boolean state){
    player.getPersistentDataContainer().set(KEY, PersistentDataType.BOOLEAN, state);
  }

  public static boolean state(@NotNull Player player, @NotNull Plugin plugin){
    return player.getPersistentDataContainer().get(KEY, PersistentDataType.BOOLEAN);
  }

}
