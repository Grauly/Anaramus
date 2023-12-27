package grauly.anaramus.datagen;

import grauly.anaramus.ModItemDefinitions;
import grauly.anaramus.recipes.AmountedIngredient;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipe;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeJsonProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.village.raid.Raid;

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
                AmountedIngredient.of(Items.GOAT_HORN),
                AmountedIngredient.of(Items.CROSSBOW),
                new AmountedIngredient(Ingredient.ofStacks(Raid.getOminousBanner()), 1),
                AmountedIngredient.of(Items.SADDLE)
        ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                true,
                ModItemDefinitions.getBadOmenPotion());
        exporter.accept(new CauldronBrewingRecipeJsonProvider(badOmenRecipe, "bad_omen"));


        var minersFervorRecipe = new CauldronBrewingRecipe(AmountedIngredient.ofMany(
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


        var greaterPoison = new CauldronBrewingRecipe(AmountedIngredient.ofMany(
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
            var luckPotion = new CauldronBrewingRecipe(
                    AmountedIngredient.concat(
                            AmountedIngredient.of(Items.SMALL_DRIPLEAF, i + 1),
                            AmountedIngredient.ofMany(
                                    Items.GLOW_BERRIES,
                                    Items.RABBIT_FOOT,
                                    Items.PINK_PETALS,
                                    Items.GLOW_LICHEN,
                                    Items.GLISTERING_MELON_SLICE
                            )
                    ),
                    Ingredient.ofItems(Items.GLASS_BOTTLE),
                    true,
                    false,
                    ModItemDefinitions.getLuckPotion(i)
            );
            exporter.accept(new CauldronBrewingRecipeJsonProvider(luckPotion, "luck_" + (i + 1)));
        }


        var blindExertion = new CauldronBrewingRecipe(
                AmountedIngredient.concat(
                        AmountedIngredient.of(Items.SUGAR, 3),
                        AmountedIngredient.of(Items.LAPIS_LAZULI, 2),
                        AmountedIngredient.ofMany(
                                Items.PHANTOM_MEMBRANE,
                                Items.GLOW_LICHEN,
                                Items.PUMPKIN
                        ),
                        AmountedIngredient.of(Items.NETHER_SPROUTS, 2),
                        AmountedIngredient.ofMany(
                                Items.PITCHER_PLANT,
                                Items.GLOW_BERRIES
                        ),
                        AmountedIngredient.of(Items.WARPED_ROOTS, 2),
                        AmountedIngredient.of(Items.HANGING_ROOTS, 3)
                ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                false,
                ModItemDefinitions.getBlindExertionPotion()
        );
        exporter.accept(new CauldronBrewingRecipeJsonProvider(blindExertion, "blind_exertion"));


    }
}
