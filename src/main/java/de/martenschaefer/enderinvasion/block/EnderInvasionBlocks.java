package de.martenschaefer.enderinvasion.block;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
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

public class EnderInvasionBlocks {

 public static EcheriteOreBlock ECHERITE_ORE = new EcheriteOreBlock(AbstractBlock.Settings.of(Material.STONE, MaterialColor.NETHER).requiresTool().strength(10.0F, 10.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS));
 public static GrassBlock END_GRASS_BLOCK = new GrassBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS));
 public static Block END_DIRT = new Block(AbstractBlock.Settings.of(Material.SOIL, MaterialColor.DIRT).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
 public static PillarBlock END_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.SPRUCE);
 public static LeavesBlock END_LEAVES = createLeavesBlock();
 public static Block END_STONE = new Block(AbstractBlock.Settings.of(Material.STONE, MaterialColor.STONE).requiresTool().strength(1.5F, 6.0F));

 public static void registerBlocks() {

  register("echerite_ore", ECHERITE_ORE, ItemGroup.MATERIALS);
  register("end_grass_block", END_GRASS_BLOCK, ItemGroup.BUILDING_BLOCKS);
  register("end_dirt", END_DIRT, ItemGroup.BUILDING_BLOCKS);
  register("end_log", END_LOG, ItemGroup.BUILDING_BLOCKS);
  register("end_leaves", END_LEAVES, ItemGroup.BUILDING_BLOCKS);
  register("end_stone", END_STONE, ItemGroup.BUILDING_BLOCKS);
 }
 private static void register(String id, Block block, ItemGroup group) {

  Registry.register(Registry.BLOCK, new Identifier(EnderInvasionMod.MOD_ID, id), block);
  Registry.register(Registry.ITEM, new Identifier(EnderInvasionMod.MOD_ID, id), new BlockItem(block, new Item.Settings().group(group)));
 }


 private static PillarBlock createLogBlock(MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
  return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> {
   return blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor;
  }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
 }
 private static LeavesBlock createLeavesBlock() {
  return new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(EnderInvasionBlocks::canSpawnOnLeaves).suffocates(EnderInvasionBlocks::never).blockVision(EnderInvasionBlocks::never));
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