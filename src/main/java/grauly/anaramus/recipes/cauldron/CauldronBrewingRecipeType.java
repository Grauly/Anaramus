package grauly.anaramus.recipes.cauldron;

import grauly.anaramus.Anaramus;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class CauldronBrewingRecipeType implements RecipeType<CauldronBrewingRecipe> {
    public static final CauldronBrewingRecipeType INSTANCE = new CauldronBrewingRecipeType();
    public static final Identifier ID = new Identifier(Anaramus.MODID, "cauldron_brewing");

    private CauldronBrewingRecipeType() {}
}
