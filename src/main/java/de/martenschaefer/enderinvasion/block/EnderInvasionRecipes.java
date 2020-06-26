package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import static de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager.addSimpleRecipe;
import static de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager.addRecipe;
import static de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager.EASY;
import static de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager.NORMAL;
import static de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager.HARD;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;

public class EnderInvasionRecipes {

 public static void registerSpreadRecipes() {

  addSimpleRecipe(EASY, new Identifier(EnderInvasionMod.MOD_ID, "end_grass_block_from_grass_block"), Blocks.GRASS_BLOCK, EnderInvasionBlocks.END_GRASS_BLOCK);
  addRecipe(EASY, new Identifier(EnderInvasionMod.MOD_ID, "end_log"), block -> BlockTags.LOGS.contains(block),
          state -> EnderInvasionBlocks.END_LOG.getDefaultState()
                  .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
  addRecipe(EASY, new Identifier(EnderInvasionMod.MOD_ID, "end_leaves"), block -> BlockTags.LEAVES.contains(block),
          state -> EnderInvasionBlocks.END_LEAVES.getDefaultState()
                  .with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE))
                  .with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)));

  NORMAL.addRecipes(EASY);
  addSimpleRecipe(NORMAL, new Identifier(EnderInvasionMod.MOD_ID, "end_grass_block_from_podzol"), Blocks.PODZOL, EnderInvasionBlocks.END_GRASS_BLOCK);
  addSimpleRecipe(NORMAL, new Identifier(EnderInvasionMod.MOD_ID, "end_dirt"), Blocks.DIRT, EnderInvasionBlocks.END_DIRT);
  addSimpleRecipe(NORMAL, new Identifier(EnderInvasionMod.MOD_ID, "end_stone"), Blocks.STONE, EnderInvasionBlocks.END_STONE);

  HARD.addRecipes(NORMAL);
 }
}