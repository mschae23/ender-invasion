package de.martenschaefer.enderinvasion;

import de.martenschaefer.enderinvasion.block.EnderInvasionBlocks;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class EnderInvasionMod implements ModInitializer {

	public static final String MOD_ID = "enderinvasion";

	public static final ComponentType<StateComponent> STATE =
		ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(MOD_ID, "state"), StateComponent.class);

	@Override
	public void onInitialize() {

  LevelComponentCallback.EVENT.register((levelProperties, components) -> components.put(STATE, new StateComponentImpl(State.PRE_ECHERITE)));

		EnderInvasionBlocks.registerBlocks();
	}
}
