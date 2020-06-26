package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.EnderInvasionUtil;
import de.martenschaefer.enderinvasion.State;
import net.minecraft.block.AbstractBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {

 @Inject(method = "randomTick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", at = @At("RETURN"))
 public void randomTick(ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

  if(world.getDimension() != DimensionType.getOverworldDimensionType()) return;
  if(world.isClient()) return;

  switch(EnderInvasionMod.STATE.get(world.getLevelProperties()).value()) {

   case ENDER_INVASION:
    if(random.nextInt(5120) == 1)  {

    EnderInvasionUtil.placeEnderInvasionPatch(world, random, pos);
    }
    EnderInvasionUtil.spreadTick(world, pos, random);
    break;
   case POST_ENDER_DRAGON:
    EnderInvasionUtil.purify(world, pos, random);
    break;
  }
 }
}