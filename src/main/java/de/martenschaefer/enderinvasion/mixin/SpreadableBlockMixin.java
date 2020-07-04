package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin extends SnowyBlock {

 protected SpreadableBlockMixin(Settings settings) {

  super(settings);
 }
 @Inject(method = "randomTick", at = @At("HEAD"))
 public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

  EnderInvasionUtil.spreadTick(state, world, pos, random);
 }
}
