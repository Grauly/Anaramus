package grauly.anaramus;

import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeSerializer;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {

    public static void setup() {
        Registry.register(Registries.RECIPE_SERIALIZER, CauldronBrewingRecipeType.ID, CauldronBrewingRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, CauldronBrewingRecipeType.ID, CauldronBrewingRecipeType.INSTANCE);
    }
}
