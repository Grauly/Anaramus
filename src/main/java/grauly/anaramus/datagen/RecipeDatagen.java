package grauly.anaramus.datagen;

import grauly.anaramus.ModItemDefinitions;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipe;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeJsonProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.village.raid.Raid;

import java.util.List;

public class RecipeDatagen extends FabricRecipeProvider {
    public RecipeDatagen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        var badOmenRecipe = new CauldronBrewingRecipe(List.of(
                Ingredient.ofItems(Items.GOAT_HORN),
                Ingredient.ofItems(Items.CROSSBOW),
                Ingredient.ofStacks(Raid.getOminousBanner()),
                Ingredient.ofItems(Items.SADDLE)),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                true,
                ModItemDefinitions.getBadOmenPotion());
        exporter.accept(new CauldronBrewingRecipeJsonProvider(badOmenRecipe, "bad_omen"));
    }
}
