package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.State;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import java.util.Random;

public class EnderInvasionGrassBlock extends GrassBlock {

 public EnderInvasionGrassBlock(Settings settings) {

  super(settings);
 }
 private static boolean canSurvive(WorldView world, BlockState state, BlockPos pos) {

  BlockPos blockPos = pos.up();
  BlockState blockState = world.getBlockState(blockPos);
  if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
   return true;
  } else if (blockState.getFluidState().getLevel() == 8) {
   return false;
  } else {
   int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
   return i < world.getMaxLightLevel();
  }
 }
 @Override
 public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

  if (!canSurvive(world, state, pos)) {

   world.setBlockState(pos, EnderInvasionBlocks.END_DIRT.getDefaultState());
  } else {

   if (EnderInvasionMod.STATE.get(world.getLevelProperties()).value() == State.ENDER_INVASION &&
           world.getLightLevel(pos.up()) >= 9) {

    for(int i = 0; i < 4; ++i) {

     BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
     if (world.getBlockState(blockPos).isOf(EnderInvasionBlocks.END_DIRT) && canSurvive(world, this.getDefaultState(), blockPos)) {
      world.setBlockState(blockPos, this.getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
     }
    }
   }
  }
 }
}
