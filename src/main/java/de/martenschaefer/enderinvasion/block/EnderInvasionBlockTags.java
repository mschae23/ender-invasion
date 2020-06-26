package de.martenschaefer.enderinvasion.block;

import net.minecraft.block.Block;
import net.minecraft.tag.GlobalTagAccessor;
import net.minecraft.tag.Tag;

public class EnderInvasionBlockTags {

 private static final GlobalTagAccessor<Block> ACCESSOR = new GlobalTagAccessor();
 public static final Tag.Identified<Block> END_LOGS = register("enderinvasion:end_logs");
 public static final Tag.Identified<Block> END_SPREADABLE = register("enderinvasion:ender_invasion_spreadable");

 private static Tag.Identified<Block> register(String id) {
  return ACCESSOR.get(id);
 }
}