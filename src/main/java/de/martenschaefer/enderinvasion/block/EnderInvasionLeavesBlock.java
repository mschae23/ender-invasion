package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class EnderInvasionLeavesBlock extends LeavesBlock {

 public EnderInvasionLeavesBlock(Settings settings) {

  super(settings);
 }
 @Override
 public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

  EnderInvasionUtil.spreadTick(state, world, pos, random);
 }
}
