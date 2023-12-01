package de.elia.bossfightcreator.world.settings;

import org.bukkit.block.Biome;

import org.jetbrains.annotations.Nullable;

//This class set the settings for the Chunkgenerator of the World "world_bossfight"
public class WorldSettings {

    private final @Nullable Biome biome;
    private final boolean caves;
    private final boolean decorations;
    private final boolean mobs;
    private final boolean noise;
    private final boolean structures;
    private final boolean surface;

    public WorldSettings(@Nullable Biome biome, boolean caves, boolean decorations, boolean mobs, boolean noise, boolean structures, boolean surface){
      this.biome = biome;
      this.caves = caves;
      this.decorations = decorations;
      this.mobs = mobs;
      this.noise = noise;
      this.structures = structures;
      this.surface = surface;

    }

    @Nullable
    public Biome biome() {
        return this.biome;
    }

    public boolean caves() {
        return this.caves;
    }

    public boolean decorations() {
        return this.decorations;
    }

    public boolean mobs() {
        return this.mobs;
    }

    public boolean noise() {
        return this.noise;
    }

    public boolean structures() {
        return this.structures;
    }

    public boolean surface() {
        return this.surface;
    }
}

