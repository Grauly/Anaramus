package grauly.anaramus.datagen;

import grauly.anaramus.ModItemDefinitions;
import grauly.anaramus.recipes.AmountedIngredient;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipe;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeJsonProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.village.raid.Raid;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RecipeDatagen extends FabricRecipeProvider {
    public RecipeDatagen(FabricDataOutput output) {
        super(output);
    }

    private static List<Ingredient> itemsToIngredients(ItemConvertible... items) {
        return Arrays.stream(items).map(Ingredient::ofItems).toList();
    }

    public static void addCauldronPotionRecipe(List<AmountedIngredient> ingredients, boolean consumeAllWater, Function<Item, ItemStack> potionGetter, String id, RecipeExporter exporter) {
        var potionRecipe = new CauldronBrewingRecipe(
                AmountedIngredient.concat(
                        AmountedIngredient.ofMany(
                                Items.BLAZE_POWDER,
                                Items.NETHER_WART
                        ),
                        ingredients
                ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                consumeAllWater,
                potionGetter.apply(Items.POTION)
        );
        var throwablePotionRecipe = new CauldronBrewingRecipe(
                AmountedIngredient.concat(
                        AmountedIngredient.ofMany(
                                Items.BLAZE_POWDER,
                                Items.NETHER_WART
                        ),
                        ingredients
                ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                consumeAllWater,
                potionGetter.apply(Items.SPLASH_POTION)
        );
        var lingeringPotionRecipe = new CauldronBrewingRecipe(
                AmountedIngredient.concat(
                        AmountedIngredient.ofMany(
                                Items.BLAZE_POWDER,
                                Items.NETHER_WART
                        ),
                        ingredients
                ),
                Ingredient.ofItems(Items.GLASS_BOTTLE),
                true,
                consumeAllWater,
                potionGetter.apply(Items.LINGERING_POTION)
        );
        exporter.accept(new CauldronBrewingRecipeJsonProvider(potionRecipe, id));
        exporter.accept(new CauldronBrewingRecipeJsonProvider(throwablePotionRecipe, "throwable_" + id));
        exporter.accept(new CauldronBrewingRecipeJsonProvider(lingeringPotionRecipe, "lingering_" + id));

    }

    @Override
    public void generate(RecipeExporter exporter) {
        addCauldronPotionRecipe(List.of(
                        AmountedIngredient.of(Items.GOAT_HORN),
                        AmountedIngredient.of(Items.CROSSBOW),
                        new AmountedIngredient(Ingredient.ofStacks(Raid.getOminousBanner()), 1),
                        AmountedIngredient.of(Items.SADDLE)
                ), true,
                ModItemDefinitions::getBadOmenPotion,
                "bad_omen",
                exporter
        );

        addCauldronPotionRecipe(AmountedIngredient.ofMany(
                        Items.EMERALD,
                        Items.MOSS_BLOCK,
                        Items.TORCHFLOWER,
                        Items.LILY_OF_THE_VALLEY,
                        Items.GLOW_BERRIES
                ), false,
                ModItemDefinitions::getMinersFervorPotion,
                "miners_fervor",
                exporter
        );

        addCauldronPotionRecipe(AmountedIngredient.ofMany(
                        Items.FERMENTED_SPIDER_EYE,
                        Items.PUFFERFISH,
                        Items.LILY_OF_THE_VALLEY,
                        Items.GLOW_LICHEN,
                        Items.WITHER_ROSE
                ), false,
                ModItemDefinitions::getGreaterPoisonPotion,
                "greater_poison",
                exporter
        );


        for (int i = 0; i < 3; i++) {

            int finalI = i; //effectively final lambda stuff
            addCauldronPotionRecipe(AmountedIngredient.concat(
                            AmountedIngredient.of(Items.SMALL_DRIPLEAF, i + 1),
                            AmountedIngredient.of(Items.GLOW_BERRIES),
                            AmountedIngredient.of(Items.RABBIT_FOOT, i + 1),
                            AmountedIngredient.ofMany(
                                    Items.PINK_PETALS,
                                    Items.GLOW_LICHEN,
                                    Items.GLISTERING_MELON_SLICE
                            )
                    ), false,
                    item -> ModItemDefinitions.getLuckPotion(item, finalI),
                    "luck_" + (i + 1),
                    exporter);
        }

        addCauldronPotionRecipe(AmountedIngredient.concat(
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
                ), true,
                ModItemDefinitions::getBlindExertionPotion,
                "blind_exertion",
                exporter
        );

    }
}
