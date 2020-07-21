package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.registry.SpreadableBlocksRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import java.util.function.Function;

public enum EnderInvasionBlocks {

 ECHERITE_ORE("echerite_ore", EcheriteOreBlock::new, FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.NETHER).build(), MaterialColor.STONE).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().strength(30.0F, 3.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS), ItemGroup.MATERIALS, false),
 END_GRASS_BLOCK("end_grass_block", EnderInvasionGrassBlock::new, AbstractBlock.Settings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS), ItemGroup.BUILDING_BLOCKS, true),
 END_DIRT("end_dirt", FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).strength(0.5F).sounds(BlockSoundGroup.GRAVEL), ItemGroup.BUILDING_BLOCKS, true),
 END_LOG("end_log", createLogSettings(MaterialColor.WOOD, MaterialColor.PURPLE), ItemGroup.BUILDING_BLOCKS, true),
 END_LEAVES("end_leaves", LeavesBlock::new, createLeavesSettings(), ItemGroup.BUILDING_BLOCKS, true);

 private final Block block;
 private final Item item;

 EnderInvasionBlocks(String id, AbstractBlock.Settings settings, ItemGroup group, boolean spreads) {

  this(id, Block::new, settings, group, spreads);
 }
 EnderInvasionBlocks(String id, Function<AbstractBlock.Settings, Block> block, AbstractBlock.Settings settings, ItemGroup group, boolean spreads) {

  this(id, block, settings, new Item.Settings().group(group), spreads);
 }
 EnderInvasionBlocks(String id, Function<AbstractBlock.Settings, Block> block, AbstractBlock.Settings settings, Item.Settings itemSettings, boolean spreads) {

  this.block = Registry.register(Registry.BLOCK, new Identifier(EnderInvasionMod.MOD_ID, id),
   block.apply(settings));
  this.item = Registry.register(Registry.ITEM, new Identifier(EnderInvasionMod.MOD_ID, id),
   new BlockItem(this.block, itemSettings));
  if(spreads) SpreadableBlocksRegistry.SPREADABLE.addBlock(this.block);
 }
 public Block get() {

  return this.block;
 }
 @SuppressWarnings("unused")
 public Item getItem() {

  return this.item;
 }

 private static AbstractBlock.Settings createLogSettings(MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
  return FabricBlockSettings.of(Material.WOOD, (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor);
 }
 private static AbstractBlock.Settings createLeavesSettings() {
  return FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(EnderInvasionBlocks::canSpawnOnLeaves).suffocates(EnderInvasionBlocks::never).blockVision(EnderInvasionBlocks::never);
 }
 private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
  return type == EntityType.OCELOT || type == EntityType.PARROT;
 }
 /**
  * A shortcut to always return {@code false} a context predicate, used as
  * {@code settings.solidBlock(Blocks::never)}.
  */
 private static boolean never(BlockState state, BlockView world, BlockPos pos) {
  return false;
 }
 public static void registerBlocks() {

  // Load class
 }
}