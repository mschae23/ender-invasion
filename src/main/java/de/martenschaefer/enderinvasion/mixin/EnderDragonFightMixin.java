package de.martenschaefer.enderinvasion.mixin;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.State;
import de.martenschaefer.enderinvasion.StateComponent;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

 @Inject(method = "dragonKilled", at = @At("RETURN"))
 public void dragonKilled(EnderDragonEntity dragon, CallbackInfo ci) {

  StateComponent state = EnderInvasionMod.STATE.get(dragon.getEntityWorld().getLevelProperties());

  if(state.value() == State.ENDER_INVASION || state.value() == State.PRE_ECHERITE) {

   state.setValue(State.POST_ENDER_DRAGON);
  }
 }
}