package grauly.anaramus.datagen;

import grauly.anaramus.ModItemDefinitions;
import grauly.anaramus.ModTags;
import grauly.anaramus.recipes.AmountedIngredient;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipe;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeJsonProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.village.raid.Raid;

import java.util.List;
import java.util.function.Function;

import static grauly.anaramus.recipes.AmountedIngredient.*;
import static net.minecraft.item.Items.*;

public class RecipeDatagen extends FabricRecipeProvider {
    public RecipeDatagen(FabricDataOutput output) {
        super(output);
    }

    public static void addCauldronPotionRecipe(List<AmountedIngredient> ingredients, boolean consumeAllWater, Function<Item, ItemStack> potionGetter, String id, RecipeExporter exporter) {
        var potionRecipe = new CauldronBrewingRecipe(
                concat(
                        ofMany(
                                BLAZE_POWDER,
                                NETHER_WART
                        ),
                        ingredients
                ),
                Ingredient.ofItems(GLASS_BOTTLE),
                true,
                consumeAllWater,
                potionGetter.apply(POTION)
        );
        var throwablePotionRecipe = new CauldronBrewingRecipe(
                concat(
                        ofMany(
                                BLAZE_POWDER,
                                NETHER_WART
                        ),
                        ingredients,
                        of(GUNPOWDER)
                ),
                Ingredient.ofItems(GLASS_BOTTLE),
                true,
                consumeAllWater,
                potionGetter.apply(SPLASH_POTION)
        );
        var lingeringPotionRecipe = new CauldronBrewingRecipe(
                concat(
                        ofMany(
                                BLAZE_POWDER,
                                NETHER_WART
                        ),
                        ingredients,
                        of(DRAGON_BREATH)
                ),
                Ingredient.ofItems(GLASS_BOTTLE),
                true,
                consumeAllWater,
                potionGetter.apply(LINGERING_POTION)
        );
        exporter.accept(new CauldronBrewingRecipeJsonProvider(potionRecipe, id));
        exporter.accept(new CauldronBrewingRecipeJsonProvider(throwablePotionRecipe, "throwable_" + id));
        exporter.accept(new CauldronBrewingRecipeJsonProvider(lingeringPotionRecipe, "lingering_" + id));

    }

    @Override
    public void generate(RecipeExporter exporter) {
        addCauldronPotionRecipe(List.of(
                        of(GOAT_HORN),
                        of(CROSSBOW),
                        new AmountedIngredient(Ingredient.ofStacks(Raid.getOminousBanner()), 1),
                        of(SADDLE)
                ), true,
                ModItemDefinitions::getBadOmenPotion,
                "bad_omen",
                exporter
        );

        addCauldronPotionRecipe(ofMany(
                        EMERALD,
                        MOSS_BLOCK,
                        TORCHFLOWER,
                        LILY_OF_THE_VALLEY,
                        GLOW_BERRIES
                ), false,
                ModItemDefinitions::getMinersFervorPotion,
                "miners_fervor",
                exporter
        );

        addCauldronPotionRecipe(ofMany(
                        FERMENTED_SPIDER_EYE,
                        PUFFERFISH,
                        LILY_OF_THE_VALLEY,
                        GLOW_LICHEN,
                        WITHER_ROSE
                ), false,
                ModItemDefinitions::getGreaterPoisonPotion,
                "greater_poison",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(SMALL_DRIPLEAF, 5),
                        of(PITCHER_PLANT, 2),
                        of(SUNFLOWER),
                        of(SEAGRASS, 2),
                        of(GOAT_HORN),
                        of(GLOW_LICHEN, 5),
                        of(HANGING_ROOTS, 4),
                        of(WARPED_ROOTS, 2),
                        of(NETHER_SPROUTS, 4),
                        of(CORNFLOWER, 3),
                        of(TORCHFLOWER),
                        of(PINK_PETALS, 13),
                        of(SPORE_BLOSSOM, 2),
                        of(EMERALD, 3),
                        of(RABBIT_FOOT, 5),
                        of(PITCHER_POD),
                        of(SCUTE, 6),
                        of(GLOW_BERRIES, 3),
                        of(GHAST_TEAR, 2),
                        of(BIG_DRIPLEAF, 9),
                        of(CHORUS_FRUIT, 5),
                        of(TOTEM_OF_UNDYING),
                        of(POPPED_CHORUS_FRUIT, 4),
                        of(ECHO_SHARD, 3)
                ), true,
                ModItemDefinitions::getFelixFelicitas,
                "felix_felicitas",
                exporter
        );


        for (int i = 0; i < 3; i++) {

            int finalI = i; //effectively final lambda stuff
            addCauldronPotionRecipe(concat(
                            of(SMALL_DRIPLEAF, i + 1),
                            of(GLOW_BERRIES),
                            of(RABBIT_FOOT, i + 1),
                            ofMany(
                                    PINK_PETALS,
                                    GLOW_LICHEN,
                                    GLISTERING_MELON_SLICE
                            )
                    ), false,
                    item -> ModItemDefinitions.getLuckPotion(item, finalI),
                    "luck_" + (i + 1),
                    exporter
            );
        }

        addCauldronPotionRecipe(concat(
                        of(SUGAR, 3),
                        of(LAPIS_LAZULI, 2),
                        ofMany(
                                PHANTOM_MEMBRANE,
                                GLOW_LICHEN,
                                PUMPKIN
                        ),
                        of(NETHER_SPROUTS, 2),
                        ofMany(
                                PITCHER_PLANT,
                                GLOW_BERRIES
                        ),
                        of(WARPED_ROOTS, 2),
                        of(HANGING_ROOTS, 3)
                ), true,
                ModItemDefinitions::getBlindExertionPotion,
                "blind_exertion",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(SUGAR, 4),
                        of(GLISTERING_MELON_SLICE),
                        of(BLAZE_POWDER, 2),
                        of(MAGMA_CREAM),
                        of(REDSTONE, 2),
                        of(ECHO_SHARD, 2),
                        of(GOAT_HORN),
                        of(CRIMSON_FUNGUS, 2),
                        of(SEA_PICKLE)
                ), true,
                ModItemDefinitions::getBerserkersRagePotion,
                "bersekers_rage",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(SUGAR, 3),
                        of(GLISTERING_MELON_SLICE),
                        of(PRISMARINE_CRYSTALS, 3),
                        of(PRISMARINE_SHARD, 5),
                        of(GLOW_INK_SAC),
                        of(SCUTE, 3),
                        of(LAPIS_LAZULI, 2),
                        of(PUFFERFISH, 1),
                        new AmountedIngredient(Ingredient.fromTag(ModTags.ALIVE_CORALS), 3),
                        of(WARPED_ROOTS, 3),
                        of(CRIMSON_ROOTS, 2)
                ), true,
                ModItemDefinitions::getNeptunesWakePotion,
                "neptunes_wake",
                exporter);

        addCauldronPotionRecipe(concat(
                        of(SUGAR),
                        of(FERMENTED_SPIDER_EYE),
                        of(REDSTONE, 2),
                        of(FERMENTED_SPIDER_EYE),
                        of(GLOW_LICHEN, 4),
                        of(CRIMSON_ROOTS),
                        of(SEA_PICKLE, 4),
                        of(PHANTOM_MEMBRANE)
                ), false,
                ModItemDefinitions::getParalysisPotion,
                "paralysis",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(MAGMA_CREAM, 3),
                        of(BLAZE_POWDER),
                        of(GLOW_BERRIES, 2),
                        of(GHAST_TEAR, 2),
                        of(REDSTONE, 2),
                        of(GLOWSTONE, 3),
                        of(PITCHER_POD),
                        of(PINK_PETALS, 4),
                        of(SUGAR_CANE, 3)
                ), false,
                ModItemDefinitions::getVitalizationPotion,
                "vitalization",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(REDSTONE, 3),
                        of(GHAST_TEAR, 2),
                        of(GOLDEN_CARROT, 6),
                        of(HANGING_ROOTS),
                        of(COCOA_BEANS, 4),
                        of(GLOW_BERRIES, 2),
                        of(CRIMSON_ROOTS)
                ), false,
                ModItemDefinitions::getGreaterHealingPotion,
                "greater_healing",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(CHORUS_FLOWER),
                        of(PINK_PETALS, 4),
                        of(PHANTOM_MEMBRANE, 2),
                        of(FEATHER, 3),
                        of(SHULKER_SHELL),
                        of(POPPED_CHORUS_FRUIT, 4)
                ), false,
                ModItemDefinitions::getFloatationPotion,
                "floatation",
                exporter
        );

        addCauldronPotionRecipe(concat(
                        of(ECHO_SHARD, 2),
                        of(MOSS_BLOCK, 2),
                        of(SOUL_SAND),
                        of(POINTED_DRIPSTONE, 4),
                        of(GUNPOWDER, 2),
                        of(QUARTZ, 4),
                        of(AMETHYST_SHARD, 2),
                        of(SCUTE),
                        of(SHULKER_SHELL)
                ), false,
                ModItemDefinitions::getStoneSkinPotion,
                "stoneskin",
                exporter
        );

    }
}
