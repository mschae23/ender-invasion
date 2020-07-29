package de.martenschaefer.enderinvasion.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

 protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {

  super(entityType, world);
 }
 @Inject(method = "canSpawn(Lnet/minecraft/world/WorldView;)Z", at = @At("RETURN"))
 public void canSpawn(WorldView world, CallbackInfoReturnable cir) {

  // TODO prevent enderman spawn before ender invasion
 }
}
