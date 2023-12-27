package grauly.anaramus.recipes.cauldron;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import grauly.anaramus.Anaramus;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

public class CauldronBrewingRecipeJsonProvider implements RecipeJsonProvider {

    private final CauldronBrewingRecipe recipe;
    private final Identifier id;

    public CauldronBrewingRecipeJsonProvider(CauldronBrewingRecipe recipe, String id) {
        this.recipe = recipe;
        this.id = new Identifier(Anaramus.MODID,id);
    }

    @Override
    public void serialize(JsonObject json) {
        JsonElement recipeJson = CauldronBrewingRecipeSerializer.CODEC.encodeStart(JsonOps.INSTANCE,recipe).result().get();
        recipeJson.getAsJsonObject().entrySet().forEach((jsonEntry) -> json.add(jsonEntry.getKey(),jsonEntry.getValue()));
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public RecipeSerializer<CauldronBrewingRecipe> serializer() {
        return CauldronBrewingRecipeSerializer.INSTANCE;
    }

    @Nullable
    @Override
    public AdvancementEntry advancement() {
        return null;
    }
}
