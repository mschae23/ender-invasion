package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.State;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Random;

@SuppressWarnings("unused")
@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

 protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {

  super(entityType, world);
 }
 @Inject(method = "canMobSpawn", at = @At("RETURN"), cancellable = true)
 private static void canMobSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable cir) {

  if(type == EntityType.ENDERMAN) {

   if(EnderInvasionMod.STATE.get(world.getLevelProperties()).value() == State.PRE_ECHERITE) {

    cir.setReturnValue(false);
   }
  }
 }
}
