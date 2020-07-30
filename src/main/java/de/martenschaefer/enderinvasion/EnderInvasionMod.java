package de.martenschaefer.enderinvasion;

import de.martenschaefer.enderinvasion.block.EnderInvasionBlocks;
import de.martenschaefer.enderinvasion.block.EnderInvasionRecipes;
import de.martenschaefer.enderinvasion.item.EnderInvasionItems;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class EnderInvasionMod implements ModInitializer {

 public static final String MOD_ID = "enderinvasion";

 public static final ComponentType<StateComponent> STATE =
         ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(MOD_ID, "state"), StateComponent.class);

 @Override
 public void onInitialize() {

  LevelComponentCallback.EVENT.register((levelProperties, components) -> components.put(STATE, new StateComponentImpl(State.PRE_ECHERITE)));

  EnderInvasionBlocks.registerBlocks();
  EnderInvasionItems.registerItems();
  EnderInvasionRecipes.registerSpreadRecipes();

  EnderInvasionHelper.changeEnderPearlTrade();

  BuiltinRegistries.BIOME.forEach(this::handleBiome);

  RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> handleBiome(biome));
 }
 private void handleBiome(Biome biome) {

  if(biome.getCategory() == Biome.Category.NETHER) {
   biome.addFeature(
           GenerationStep.Feature.UNDERGROUND_DECORATION,
           Feature.ORE.configure(
                   new OreFeatureConfig(
                           OreFeatureConfig.Rules.NETHERRACK,
                           EnderInvasionBlocks.ECHERITE_ORE.get().getDefaultState(),
                           4 //Ore vein size
                   )).decorate(
                   Decorator.RANGE.configure(new RangeDecoratorConfig(
                           0, //Bottom Offset
                           3, //Min y level
                           125 //Max y level
                   )).spreadHorizontally()));
  }
 }
}