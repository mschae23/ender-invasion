package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import de.martenschaefer.enderinvasion.registry.SpreadableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SnowyBlock;
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

public class EnderInvasionBlocks {

 public static EcheriteOreBlock ECHERITE_ORE = new EcheriteOreBlock(AbstractBlock.Settings.of(Material.STONE, MaterialColor.NETHER).ticksRandomly().requiresTool().strength(10.0F, 10.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS));
 public static EnderInvasionBlock END_GRASS_BLOCK = new EnderInvasionBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS));
 public static EnderInvasionBlock END_DIRT = new EnderInvasionBlock(AbstractBlock.Settings.of(Material.SOIL, MaterialColor.DIRT).ticksRandomly().strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
 public static EnderInvasionPillarBlock END_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.SPRUCE);
 public static EnderInvasionLeavesBlock END_LEAVES = createLeavesBlock();
 public static EnderInvasionBlock END_STONE = new EnderInvasionBlock(AbstractBlock.Settings.of(Material.STONE, MaterialColor.STONE).ticksRandomly().requiresTool().strength(1.5F, 6.0F));

 public static void registerBlocks() {

  register("echerite_ore", ECHERITE_ORE, ItemGroup.MATERIALS, false);
  register("end_grass_block", END_GRASS_BLOCK, ItemGroup.BUILDING_BLOCKS, true);
  register("end_dirt", END_DIRT, ItemGroup.BUILDING_BLOCKS, true);
  register("end_log", END_LOG, ItemGroup.BUILDING_BLOCKS, true);
  register("end_leaves", END_LEAVES, ItemGroup.BUILDING_BLOCKS, true);
  register("end_stone", END_STONE, ItemGroup.BUILDING_BLOCKS, true);
 }
 private static void register(String id, Block block, ItemGroup group, boolean spreads) {

  Registry.register(Registry.BLOCK, new Identifier(EnderInvasionMod.MOD_ID, id), block);
  Registry.register(Registry.ITEM, new Identifier(EnderInvasionMod.MOD_ID, id), new BlockItem(block, new Item.Settings().group(group)));
  if(spreads) SpreadableBlocksRegistry.SPREADABLE.addBlock(block);
 }


 private static EnderInvasionPillarBlock createLogBlock(MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
  return new EnderInvasionPillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> {
   return blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor;
  }).ticksRandomly().strength(2.0F).sounds(BlockSoundGroup.WOOD));
 }
 private static EnderInvasionLeavesBlock createLeavesBlock() {
  return new EnderInvasionLeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(EnderInvasionBlocks::canSpawnOnLeaves).suffocates(EnderInvasionBlocks::never).blockVision(EnderInvasionBlocks::never));
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
}