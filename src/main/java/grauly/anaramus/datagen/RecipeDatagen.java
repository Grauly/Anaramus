package grauly.anaramus.datagen;

import grauly.anaramus.ModItemDefinitions;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipe;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeJsonProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.village.raid.Raid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeDatagen extends FabricRecipeProvider {
    public RecipeDatagen(FabricDataOutput output) {
        super(output);
    }

    private static List<Ingredient> itemsToIngredients(ItemConvertible... items) {
        return Arrays.stream(items).map(Ingredient::ofItems).toList();
    }

    @Override
    public void generate(RecipeExporter exporter) {
        var badOmenRecipe = new CauldronBrewingRecipe(List.of(
                Ingredient.ofItems(Items.GOAT_HORN),
                Ingredient.ofItems(Items.CROSSBOW),
                Ingredient.ofStacks(Raid.getOminousBanner()),
                Ingredient.ofItems(Items.SADDLE)
        ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                true,
                ModItemDefinitions.getBadOmenPotion());
        exporter.accept(new CauldronBrewingRecipeJsonProvider(badOmenRecipe, "bad_omen"));


        var minersFervorRecipe = new CauldronBrewingRecipe(itemsToIngredients(
                Items.EMERALD,
                Items.MOSS_BLOCK,
                Items.TORCHFLOWER,
                Items.LILY_OF_THE_VALLEY,
                Items.GLOW_BERRIES
        ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                false,
                ModItemDefinitions.getMinersFervorPotion()
        );
        exporter.accept(new CauldronBrewingRecipeJsonProvider(minersFervorRecipe, "miners_fervor"));


        var greaterPoison = new CauldronBrewingRecipe(itemsToIngredients(
                Items.FERMENTED_SPIDER_EYE,
                Items.PUFFERFISH,
                Items.LILY_OF_THE_VALLEY,
                Items.GLOW_LICHEN,
                Items.WITHER_ROSE
        ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                false,
                ModItemDefinitions.getMinersFervorPotion()
        );
        exporter.accept(new CauldronBrewingRecipeJsonProvider(greaterPoison, "greater_poison"));


        for (int i = 0; i < 3; i++) {
            var ingredients = new ArrayList<>(List.of(Ingredient.ofStacks(new ItemStack(Items.SMALL_DRIPLEAF, i + 1))));
            ingredients.addAll(
                    itemsToIngredients(
                            Items.GLOW_BERRIES,
                            Items.RABBIT_FOOT,
                            Items.PINK_PETALS,
                            Items.GLOW_LICHEN,
                            Items.GLISTERING_MELON_SLICE
                    ));
            var luckPotion = new CauldronBrewingRecipe(
                    ingredients,
                    Ingredient.ofItems(Items.GLASS_BOTTLE),
                    true,
                    false,
                    ModItemDefinitions.getLuckPotion(i+1)
            );
            exporter.accept(new CauldronBrewingRecipeJsonProvider(luckPotion, "luck_" + (i + 1)));
        }
    }
}