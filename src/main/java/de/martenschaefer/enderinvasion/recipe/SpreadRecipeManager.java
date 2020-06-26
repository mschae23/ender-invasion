package de.martenschaefer.enderinvasion.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

public class SpreadRecipeManager {
 
	public static final SpreadRecipeManager EASY = new SpreadRecipeManager();
	public static final SpreadRecipeManager NORMAL = new SpreadRecipeManager();
	public static final SpreadRecipeManager HARD = new SpreadRecipeManager();
	
	private final HashMap<Identifier, SpreadRecipe> recipes;
	
	private SpreadRecipeManager() {
		
		this.recipes = new HashMap<>();
	}
	public void addRecipe(SpreadRecipe recipe) {
		
		recipes.put(recipe.getIdentifier(), recipe);
	}
	public void addRecipes(SpreadRecipeManager recipes) {

		recipes.recipes.forEach((id, recipe) -> this.addRecipe(recipe));
	}
	public SpreadRecipe getRecipe(Identifier identifier) {
		
	 return recipes.get(identifier);
	}
	public SpreadRecipe getRecipe(Block input) {
		
		for(Entry<Identifier, SpreadRecipe> entry: recipes.entrySet()) {
			
			if(entry.getValue().isBlockValid(input)) return entry.getValue();
		}
		return null;
	}
	public static void addSimpleRecipe(SpreadRecipeManager manager, Identifier identifier, Block input, Block result) {

		addRecipe(manager, identifier, block -> block == input, state -> result.getDefaultState());
	}
	public static void addRecipe(SpreadRecipeManager manager, Identifier identifier, Predicate<Block> isValidBlock, Function<BlockState, BlockState> convert) {

		manager.addRecipe(new SpreadRecipe(identifier, isValidBlock, convert));
	}
}