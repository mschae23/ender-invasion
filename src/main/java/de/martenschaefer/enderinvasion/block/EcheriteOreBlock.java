package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.State;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class EcheriteOreBlock extends OreBlock {

 public EcheriteOreBlock(Settings settings) {

  super(settings);
 }
 @Override
 public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {

  super.afterBreak(world, player, pos, state, blockEntity, stack);

  if(EnderInvasionMod.STATE.get(world.getLevelProperties()).value() == State.PRE_ECHERITE) {

   EnderInvasionMod.STATE.get(world.getLevelProperties()).setValue(State.ENDER_INVASION);

   // Send chat message
   world.getPlayers().forEach(p -> {
    player.sendMessage(new TranslatableText("enderinvasion.start").formatted(Formatting.DARK_GREEN), false);
   });
  }
 }
}
