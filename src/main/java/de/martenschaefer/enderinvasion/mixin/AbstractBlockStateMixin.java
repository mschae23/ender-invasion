package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.SpreadUtil;
import de.martenschaefer.enderinvasion.State;
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

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {

 @Inject(method = "randomTick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", at = @At("RETURN"))
 public void randomTick(ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

  if(!EnderInvasionBlockTags.END_SPREADABLE.contains(world.getBlockState(pos).getBlock())) return;
  if(EnderInvasionMod.STATE.get(world.getLevelProperties()).value() != State.ENDER_INVASION) return;

  /* if (!canSurvive(state, world, pos)) {

   world.setBlockState(pos, getSurviveBlock().getDefaultState());
   return;
  } */

  Difficulty difficulty = world.getDifficulty();

  for (int i = 0; i < difficulty.getId(); i++) {

   BlockPos blockPos = pos.add(SpreadUtil.randomNearbyBlockPos(difficulty, random));
   SpreadUtil.spreadTo(difficulty, world, pos, blockPos, random);
  }
 }
}