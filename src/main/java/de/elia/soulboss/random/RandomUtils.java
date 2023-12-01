package de.elia.soulboss.random;

import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

//This Random generator equiped a random item to the Zombie boss
public class RandomUtils {
  public void randomItem(Zombie zombie, float chance, org.bukkit.inventory.ItemStack item1, org.bukkit.inventory.ItemStack item2) {
    Random random = new Random();
    float x = random.nextFloat();
    if (x < chance) {
      zombie.equipItemIfPossible(ItemStack.fromBukkitCopy(item1));
    } else {
      zombie.equipItemIfPossible(ItemStack.fromBukkitCopy(item2));
    }
  }
}
