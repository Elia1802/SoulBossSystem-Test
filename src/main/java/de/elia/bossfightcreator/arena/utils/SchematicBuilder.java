package de.elia.bossfightcreator.arena.utils;

import de.elia.api.logging.error.SaveError;

import de.elia.bossfightcreator.BossFightCreatorMain;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import org.bukkit.Location;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//Coding by Sinan and Elia
//Load and paste the schematics in the Bossfightworld
public class SchematicBuilder {

  //Coding by Sinan
  //Gets the Schematic
  @NotNull
  public static Clipboard schematic(@NotNull File path, @NotNull String schematicName) {
    Clipboard clipboard;
    File arenaFile = new File(path, schematicName + ".schem");
    ClipboardFormat format = ClipboardFormats.findByFile(arenaFile);
    try (ClipboardReader reader = format.getReader(new FileInputStream(arenaFile))) {
      return clipboard = reader.read();
    }catch (IOException exception) {
      //Start Elia
      SaveError.saveError(BossFightCreatorMain.bossFightCreator().main(), exception, "SchematicBuilder-schematic-line_39-40=ioexception");
      exception.printStackTrace();
      return null;
      //End Elia
    }
  }

  //Coding by Sinan
  public static void pasteSchematic(@NotNull Location location, @NotNull Clipboard clipboard) throws WorldEditException {
    EditSession session = WorldEdit.getInstance().newEditSession(new BukkitWorld(location.getWorld()));Operation operation = new ClipboardHolder(clipboard).createPaste(session).to(BlockVector3.at(location.x(), location.y(), location.z())).ignoreAirBlocks(false).copyEntities(false).build();
    Operations.complete(operation);
    session.close();
  }
}
