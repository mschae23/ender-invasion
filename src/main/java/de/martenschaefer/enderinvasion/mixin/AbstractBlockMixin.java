package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.EnderInvasionUtil;
import de.martenschaefer.enderinvasion.State;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

 @Inject(method = "randomTick", at = @At("HEAD"))
 public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

  if(world.getDimension() != DimensionType.getOverworldDimensionType()) return;
  if(world.isClient()) return;

  switch(EnderInvasionMod.STATE.get(world.getLevelProperties()).value()) {

   case ENDER_INVASION:
    if(random.nextInt(5120) == 1)  {

    EnderInvasionUtil.placeEnderInvasionPatch(world, random, pos);
    }
    EnderInvasionUtil.spreadTick(state, world, pos, random);
    break;
   case POST_ENDER_DRAGON:
    EnderInvasionUtil.purify(state, world, pos, random);
    break;
  }
 }
}