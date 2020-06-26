package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.SpreadUtil;
import net.minecraft.block.AbstractBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {

 @Inject(method = "randomTick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", at = @At("RETURN"))
 public void randomTick(ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

  SpreadUtil.spreadTick(world, pos, random);
 }
}