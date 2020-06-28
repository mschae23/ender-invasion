package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class EnderInvasionPillarBlock extends PillarBlock {

 public EnderInvasionPillarBlock(Settings settings) {

  super(settings);
 }
 @Override
 public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

  EnderInvasionUtil.randomTick(state, world, pos, random);
 }
}