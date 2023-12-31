package de.elia.items.items.zombiespawnegg;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.components.ComplexItem;

import de.elia.api.thezepserapi.enums.Type;
import de.elia.items.ItemMain;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

//This class creates the zombie boss spawn egg
public class Zombie_Spawn_Egg {
  private static ComplexItem ZSE;

  public Zombie_Spawn_Egg(Plugin plugin) {
    if (ZSE == null) {
      ArrayList<Component> list = new ArrayList<>();
      list.add(ItemMain.MINI_MESSAGE.deserialize("<gray>With this egg you can spawn the</gray> <dark_green>Zombie Boss</dark_green>"));
      Component name = ItemMain.MINI_MESSAGE.deserialize("<dark_green>Zombie Boss</dark_green> <gray>Spawn Egg</gray>");
      ZSE = TheZepserAPI.Item.create(Material.ZOMBIE_SPAWN_EGG, name, list)
              .setCustomModelData(1)
              .setAmount(1)
              .setKey(Complex.ZOMBIE_SPAWN_EGG, plugin)
              .setType(Type.SPAWN_EGG)
              .save();
    }
  }
}
