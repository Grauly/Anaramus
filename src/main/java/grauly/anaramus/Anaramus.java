package grauly.anaramus;

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeSerializer;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static grauly.anaramus.ModItems.ANARAMUS_CONTENT_ITEM_GROUP;

public class Anaramus implements ModInitializer {
    public static final String MODID = "anaramus";

    @Override
    public void onInitialize() {
        PolymerItemGroupUtils.registerPolymerItemGroup(new Identifier(MODID, "anaramus_content"), ANARAMUS_CONTENT_ITEM_GROUP);
        Registry.register(Registries.RECIPE_SERIALIZER, CauldronBrewingRecipeType.ID, new CauldronBrewingRecipeSerializer());
        Registry.register(Registries.RECIPE_TYPE, CauldronBrewingRecipeType.ID, CauldronBrewingRecipeType.INSTANCE);
    }
}
