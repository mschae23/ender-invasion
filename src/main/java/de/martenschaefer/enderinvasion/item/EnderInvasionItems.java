package de.martenschaefer.enderinvasion.item;

import de.martenschaefer.enderinvasion.EnderInvasionMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import java.util.function.Function;

public enum EnderInvasionItems {

 ECHERITE_INGOT("echerite_ingot", ItemGroup.MATERIALS);

 private final Item item;

 EnderInvasionItems(String id, ItemGroup group) {

  this(id, Item::new, group);
 }
 EnderInvasionItems(String id, Function<Item.Settings, Item> item, ItemGroup group) {

  this(id, item, new Item.Settings().group(group));
 }
 EnderInvasionItems(String id, Function<Item.Settings, Item> item, Item.Settings settings) {

  this.item = Registry.register(Registry.ITEM, new Identifier(EnderInvasionMod.MOD_ID, id),
          item.apply(settings));
 }
 public Item get() {

  return this.item;
 }
 public static void registerItems() {

  // Load class
 }
}
