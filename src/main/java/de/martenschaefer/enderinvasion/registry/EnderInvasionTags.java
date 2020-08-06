package de.martenschaefer.enderinvasion.registry;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class EnderInvasionTags {

 public static final Tag<Block> SPREADABLE = TagRegistry.block(new Identifier(EnderInvasionMod.MOD_ID, "spreadable"));
 public static final Tag<Block> END_LOGS = TagRegistry.block(new Identifier(EnderInvasionMod.MOD_ID, "end_logs"));
}