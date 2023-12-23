package grauly.anaramus;

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import static grauly.anaramus.ModItems.ANARAMUS_CONTENT_ITEM_GROUP;

public class Anaramus implements ModInitializer {
    public static final String MODID = "anaramus";

    @Override
    public void onInitialize() {
        PolymerItemGroupUtils.registerPolymerItemGroup(new Identifier(MODID, "anaramus_content"), ANARAMUS_CONTENT_ITEM_GROUP);
    }
}
