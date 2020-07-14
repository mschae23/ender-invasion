package de.martenschaefer.enderinvasion;

import de.martenschaefer.enderinvasion.registry.SpreadRecipe;
import de.martenschaefer.enderinvasion.registry.SpreadRecipeManager;
import de.martenschaefer.enderinvasion.registry.SpreadableBlocksRegistry;
import de.martenschaefer.enderinvasion.worldgen.EnderInvasionPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Difficulty;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;
import java.util.Random;

public class EnderInvasionUtil {

 public static void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

  if(world.getDimension() != DimensionType.getOverworldDimensionType()) return;
  if(world.isClient()) return;

  switch(EnderInvasionMod.STATE.get(world.getLevelProperties()).value()) {

   case ENDER_INVASION:
    EnderInvasionUtil.infect(state, world, pos, random);
    EnderInvasionUtil.spread(state, world, pos, random);
    break;
   case POST_ENDER_DRAGON:
    EnderInvasionUtil.purify(state, world, pos, random);
    break;
  }
 }
 public static void infect(@SuppressWarnings("unused") BlockState state, ServerWorld world, BlockPos pos, Random random) {

  if(random.nextInt(16384) == 1)  {

    EnderInvasionUtil.placeEnderInvasionPatch(world, random, pos);
  }
 }
 public static void spread(BlockState state, ServerWorld world, BlockPos pos, Random random) {

  if(!SpreadableBlocksRegistry.SPREADABLE.test(state.getBlock())) return;

  if (!EnderInvasionUtil.canSurvive(world, state, pos)) {

   SpreadRecipe recipe = SpreadRecipeManager.WATER_PURIFICATION.getRecipe(state.getBlock());
   if(recipe == null) return;
   world.setBlockState(pos, recipe.convert(state));
   return;
  }
  Difficulty difficulty = world.getDifficulty();

  for (int i = 0; i < difficulty.getId(); i++) {

   BlockPos blockPos = pos.add(randomNearbyBlockPos(difficulty, random));
   spreadTo(difficulty, world, blockPos, random);
  }
 }
 public static void spreadTo(Difficulty difficulty, ServerWorld world, BlockPos to, @SuppressWarnings("unused") Random random) {

  BlockState blockState = world.getBlockState(to);
  Block block = blockState.getBlock();

  SpreadRecipe recipe = getRecipeForBlock(difficulty, block);

  if(recipe == null) return;

  BlockState resultBlockState = recipe.convert(blockState);
  world.setBlockState(to, resultBlockState);
 }
 public static boolean canSurvive(WorldView world, BlockState state, BlockPos pos) {

  BlockPos posUp = pos.up();
  BlockState stateUp = world.getBlockState(posUp);
  if(!world.getFluidState(posUp).isIn(FluidTags.WATER) && !stateUp.isOpaque()) return true;

  for(Direction direction: Direction.values()) {

   BlockPos pos2 = pos.add(direction.getVector());
   if(world.getFluidState(pos2).isIn(FluidTags.WATER)) return false;
  }
  return true;
 }
 public static void placeEnderInvasionPatch(ServerWorld world, Random random, BlockPos blockPos) {

  EnderInvasionPlacer.generate(world, random, blockPos, 30);
 }
 public static void purify(BlockState state, ServerWorld world, BlockPos pos, @SuppressWarnings("unused") Random random) {

  if(SpreadRecipeManager.PURIFICATION.getRecipe(state.getBlock()) == null) return;

  SpreadRecipe recipe = SpreadRecipeManager.PURIFICATION.getRecipe(state.getBlock());
  world.setBlockState(pos, recipe.convert(state));
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