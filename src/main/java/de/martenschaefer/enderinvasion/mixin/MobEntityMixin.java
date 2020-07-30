package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.State;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("unused")
@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

 protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {

  super(entityType, world);
 }
 @Redirect(method = "canMobSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$AbstractBlockState;allowsSpawning(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/EntityType;)Z"))
 private static boolean preventEndermanSpawn(AbstractBlock.AbstractBlockState state, BlockView world, BlockPos pos, EntityType<?> type) {

  if(type == EntityType.ENDERMAN) {

   if(EnderInvasionMod.STATE.get(((WorldAccess) world).getLevelProperties()).value() == State.PRE_ECHERITE) {

    return false;
   }
  }
  return state.allowsSpawning(world, pos, type);
 }
}
