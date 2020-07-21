package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.registry.SpreadableBlocksRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.EASY;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.HARD;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.NORMAL;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.PURIFICATION;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.WATER_PURIFICATION;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.addRecipe;
import static de.martenschaefer.enderinvasion.registry.SpreadRecipeManager.addSimpleRecipe;

public class EnderInvasionRecipes {

 public static void registerSpreadRecipes() {

  addSimpleRecipe(EASY, new Identifier(EnderInvasionMod.MOD_ID, "end_grass_block_from_grass_block"), Blocks.GRASS_BLOCK, EnderInvasionBlocks.END_GRASS_BLOCK.get());
  addSimpleRecipe(NORMAL, new Identifier(EnderInvasionMod.MOD_ID, "end_dirt"), Blocks.DIRT, EnderInvasionBlocks.END_DIRT.get());
  addRecipe(EASY, new Identifier(EnderInvasionMod.MOD_ID, "end_log"), BlockTags.LOGS::contains,
          state -> EnderInvasionBlocks.END_LOG.get().getDefaultState()
                  .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
  addRecipe(EASY, new Identifier(EnderInvasionMod.MOD_ID, "end_leaves"), BlockTags.LEAVES::contains,
          state -> EnderInvasionBlocks.END_LEAVES.get().getDefaultState()
                  .with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE))
                  .with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)));

  NORMAL.addRecipes(EASY);
  addSimpleRecipe(NORMAL, new Identifier(EnderInvasionMod.MOD_ID, "end_grass_block_from_podzol"), Blocks.PODZOL, EnderInvasionBlocks.END_GRASS_BLOCK.get());
  // addSimpleRecipe(NORMAL, new Identifier(EnderInvasionMod.MOD_ID, "end_sand", Blocks.SAND, EnderInvasionBlocks.END_SAND);

  HARD.addRecipes(NORMAL);
  addSimpleRecipe(HARD, new Identifier(EnderInvasionMod.MOD_ID, "end_grass_block_from_mycelium"), Blocks.MYCELIUM, EnderInvasionBlocks.END_GRASS_BLOCK.get());
  addSimpleRecipe(HARD, new Identifier(EnderInvasionMod.MOD_ID, "end_stone"), Blocks.STONE, Blocks.END_STONE);
  // addSimpleRecipe(HARD, new Identifier(EnderInvasionMod.MOD_ID, "end_sandstone"), Blocks.SANDSTONE, EnderInvasionBlocks.END_SANDSTONE);

  addSimpleRecipe(PURIFICATION, new Identifier(EnderInvasionMod.MOD_ID, "grass_block_purification"), EnderInvasionBlocks.END_GRASS_BLOCK.get(), Blocks.GRASS_BLOCK);
  addSimpleRecipe(PURIFICATION, new Identifier(EnderInvasionMod.MOD_ID, "dirt_purification"), EnderInvasionBlocks.END_DIRT.get(), Blocks.DIRT);
  addSimpleRecipe(PURIFICATION, new Identifier(EnderInvasionMod.MOD_ID, "stone_purification"), Blocks.END_STONE, Blocks.STONE);

  WATER_PURIFICATION.addRecipes(PURIFICATION);
  addRecipe(WATER_PURIFICATION, new Identifier(EnderInvasionMod.MOD_ID, "end_log_purification"), EnderInvasionBlocks.END_LOG.get()::equals,
          state -> Blocks.OAK_LOG.getDefaultState()
                  .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
  addRecipe(WATER_PURIFICATION, new Identifier(EnderInvasionMod.MOD_ID, "end_leaves_purification"), EnderInvasionBlocks.END_LEAVES.get()::equals,
          state -> Blocks.OAK_LEAVES.getDefaultState()
                  .with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE))
                  .with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)));

  SpreadableBlocksRegistry.SPREADABLE.addBlock(Blocks.END_STONE);
 }
}