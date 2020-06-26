package de.martenschaefer.enderinvasion;

import de.martenschaefer.enderinvasion.recipe.SpreadRecipe;
import de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Difficulty;

import java.util.Random;

public class SpreadUtil {

 public static boolean spreadTo(Difficulty difficulty, ServerWorld world, BlockPos from, BlockPos to, Random random) {

  BlockState blockState = world.getBlockState(to);
  Block block = blockState.getBlock();

  SpreadRecipe recipe = getRecipeForBlock(difficulty, block);

  if(recipe == null) return false;

  BlockState resultBlockState = recipe.convert(blockState);
  return world.setBlockState(to, resultBlockState);
 }
 public static Vec3i randomNearbyBlockPos(Difficulty difficulty, Random random) {

  if(difficulty == Difficulty.HARD)
   return new Vec3i(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
  else
   return new Vec3i(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
 }
 public static SpreadRecipe getRecipeForBlock(Difficulty difficulty, Block block) {

  if(difficulty == Difficulty.EASY) return SpreadRecipeManager.EASY.getRecipe(block);
  if(difficulty == Difficulty.NORMAL) return SpreadRecipeManager.NORMAL.getRecipe(block);
  if(difficulty == Difficulty.HARD) return SpreadRecipeManager.HARD.getRecipe(block);
  else return null;
 }
}