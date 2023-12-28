package grauly.anaramus.recipes.cauldron;

import eu.pb4.polymer.core.api.item.PolymerRecipe;
import grauly.anaramus.recipes.AmountedIngredient;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CauldronBrewingRecipe implements Recipe<SimpleInventory>, PolymerRecipe {

    private final ArrayList<AmountedIngredient> ingredients;
    private final Ingredient activationIngredient;
    private final boolean needsFire;
    private final boolean consumeAllWater;
    private final ItemStack result;

    public CauldronBrewingRecipe(List<AmountedIngredient> ingredients, Ingredient activationIngredient, boolean needsFire, boolean consumeAllWater, ItemStack result) {
        this.ingredients = new ArrayList<>(ingredients);
        this.activationIngredient = activationIngredient;
        this.needsFire = needsFire;
        this.consumeAllWater = consumeAllWater;
        this.result = result;
    }


    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (inventory.stacks.size() != ingredients.size()) return false;
        boolean correct = true;
        for (int i = 0; i < ingredients.size(); i++) {
            correct &= ingredients.get(i).ingredient().test(inventory.stacks.get(i)) && ingredients.get(i).amount() == inventory.stacks.get(i).getCount();
        }
        return correct;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CauldronBrewingRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return CauldronBrewingRecipeType.INSTANCE;
    }

    public boolean needsFire() {
        return needsFire;
    }

    public List<AmountedIngredient> getCauldronIngredients() {
        return ingredients;
    }

    public ItemStack getRecipeResult() {
        return result;
    }

    public Ingredient getActivationIngredient() {
        return activationIngredient;
    }

    public boolean consumesAllWater() {
        return consumeAllWater;
    }
}
