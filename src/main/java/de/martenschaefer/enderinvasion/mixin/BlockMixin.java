package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.block.EnderInvasionBlockTags;
import de.martenschaefer.enderinvasion.recipe.SpreadRecipe;
import de.martenschaefer.enderinvasion.recipe.SpreadRecipeManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractBlock.class)
public class BlockMixin {

 @Inject(method = "randomTick", at = @At("RETURN"))
 public void randomTick(ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

  if(!EnderInvasionBlockTags.END_SPREADABLE.contains(world.getBlockState(pos).getBlock())) return;

  /* if (!canSurvive(state, world, pos)) {

   world.setBlockState(pos, getSurviveBlock().getDefaultState());
   return;
  } */

  Difficulty difficulty = world.getDifficulty();

  for (int i = 0; i < difficulty.getId(); i++) {

   BlockPos blockPos = pos.add(randomNearbyBlockPos(difficulty, random));
   this.spreadTo(difficulty, world, pos, blockPos, random);
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