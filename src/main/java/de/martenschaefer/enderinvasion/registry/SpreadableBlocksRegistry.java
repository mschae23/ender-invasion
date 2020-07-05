package de.martenschaefer.enderinvasion.registry;

import net.minecraft.block.Block;

import java.util.HashSet;
import java.util.function.Predicate;

public class SpreadableBlocksRegistry {

 public static SpreadableBlocksRegistry SPREADABLE = new SpreadableBlocksRegistry();

 private final HashSet<Predicate<Block>> blocks;

 public SpreadableBlocksRegistry() {

  this.blocks = new HashSet<>();
 }
 public void addBlock(Block block) {

  this.blocks.add((test -> test.equals(block)));
 }
 public void addBlock(Predicate<Block> predicate) {

  this.blocks.add(predicate);
 }
 public void addBlocks(SpreadableBlocksRegistry blocks) {

  this.blocks.addAll(blocks.blocks);
 }
 public boolean test(Block block) {

  for(Predicate<Block> predicate: blocks) {

   if(predicate.test(block)) return true;
  }
  return false;
 }
}