package grauly.anaramus.recipes.cauldron;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;

public class CauldronBrewingRecipeSerializer implements RecipeSerializer<CauldronBrewingRecipe> {

    public static final Codec<CauldronBrewingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(CauldronBrewingRecipe::getCauldronIngredients),
            Codec.BOOL.fieldOf("needsFire").forGetter(CauldronBrewingRecipe::needsFire),
            ItemStack.CODEC.fieldOf("result").forGetter(CauldronBrewingRecipe::getRecipeResult)
    ).apply(instance,CauldronBrewingRecipe::new));

    @Override
    public Codec<CauldronBrewingRecipe> codec() {
        return CODEC;
    }

    @Override
    public CauldronBrewingRecipe read(PacketByteBuf buf) {
        return buf.decodeAsJson(CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, CauldronBrewingRecipe recipe) {
        buf.encodeAsJson(CODEC,recipe);
    }
}
