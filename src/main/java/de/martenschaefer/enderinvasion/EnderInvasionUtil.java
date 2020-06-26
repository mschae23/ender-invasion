package de.martenschaefer.enderinvasion;

import de.martenschaefer.enderinvasion.registry.SpreadRecipe;
import de.martenschaefer.enderinvasion.registry.SpreadRecipeManager;
import de.martenschaefer.enderinvasion.registry.SpreadableBlocksRegistry;
import de.martenschaefer.enderinvasion.worldgen.EnderInvasionPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorldAccess;

import java.util.Random;

public class EnderInvasionUtil {

 public static void spreadTick(ServerWorld world, BlockPos pos, Random random) {

  if(!SpreadableBlocksRegistry.SPREADABLE.test(world.getBlockState(pos).getBlock())) return;

  /* if (!canSurvive(state, world, pos)) {

   world.setBlockState(pos, getSurviveBlock().getDefaultState());
   return;
  } */

  Difficulty difficulty = world.getDifficulty();

  for (int i = 0; i < difficulty.getId(); i++) {

   BlockPos blockPos = pos.add(EnderInvasionUtil.randomNearbyBlockPos(difficulty, random));
   spreadTo(difficulty, world, pos, blockPos, random);
  }
 }
 public static boolean spreadTo(Difficulty difficulty, ServerWorld world, BlockPos from, BlockPos to, Random random) {

  BlockState blockState = world.getBlockState(to);
  Block block = blockState.getBlock();

  SpreadRecipe recipe = getRecipeForBlock(difficulty, block);

  if(recipe == null) return false;

  BlockState resultBlockState = recipe.convert(blockState);
  return world.setBlockState(to, resultBlockState);
 }
 public static void placeEnderInvasionPatch(ServerWorldAccess world, Random random, BlockPos blockPos) {

  EnderInvasionPlacer.generate(world, random, blockPos, 15);
 }
 public static void purify(ServerWorld world, BlockPos pos, Random random) {

  if(SpreadRecipeManager.PURIFICATION.getRecipe(world.getBlockState(pos).getBlock()) == null) return;

  SpreadRecipe recipe = SpreadRecipeManager.PURIFICATION.getRecipe(world.getBlockState(pos).getBlock());
  world.setBlockState(pos, recipe.convert(world.getBlockState(pos)));
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