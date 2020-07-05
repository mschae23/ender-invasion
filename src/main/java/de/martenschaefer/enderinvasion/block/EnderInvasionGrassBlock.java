package de.martenschaefer.enderinvasion.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Random;

public class EnderInvasionGrassBlock extends GrassBlock {

 public EnderInvasionGrassBlock(Settings settings) {

  super(settings);
 }
 @Override
 public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

  // Remove SpreadableBlock randomTick logic
 }
}
