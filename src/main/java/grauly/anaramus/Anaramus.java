package grauly.anaramus;

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeSerializer;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static grauly.anaramus.ModItems.ANARAMUS_CONTENT_ITEM_GROUP;

public class Anaramus implements ModInitializer {
    public static final String MODID = "anaramus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        PolymerItemGroupUtils.registerPolymerItemGroup(new Identifier(MODID, "anaramus_content"), ANARAMUS_CONTENT_ITEM_GROUP);
        ModRecipes.setup();
        ModBlockEntities.setup();
        LOGGER.info("Setup complete");
    }
}
