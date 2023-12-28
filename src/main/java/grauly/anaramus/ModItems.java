package grauly.anaramus;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

import static grauly.anaramus.Anaramus.MODID;

public class ModItems {
    public static final Item POTION_CAULDRON_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID, "potion_cauldron"), new PolymerBlockItem(ModBlocks.POTION_CAULDRON, new FabricItemSettings().rarity(Rarity.UNCOMMON), Items.CAULDRON));

    public static final ItemGroup ANARAMUS_CONTENT_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(POTION_CAULDRON_ITEM))
            .displayName(Text.translatable("itemGroup.anaramus.main"))
            .entries((context, entries) -> {
                entries.add(POTION_CAULDRON_ITEM);
                putAllPotionVariants(entries, ModItemDefinitions::getBadOmenPotion);
                putAllPotionVariants(entries, ModItemDefinitions::getMinersFervorPotion);
                putAllPotionVariants(entries, ModItemDefinitions::getGreaterPoisonPotion);
                putAllPotionVariants(entries, ModItemDefinitions::getFelixFelicitas);
                putAllPotionVariants(entries, item -> ModItemDefinitions.getLuckPotion(item,1));
                putAllPotionVariants(entries, item -> ModItemDefinitions.getLuckPotion(item,2));
                putAllPotionVariants(entries, item -> ModItemDefinitions.getLuckPotion(item,3));
                putAllPotionVariants(entries, ModItemDefinitions::getBlindExertionPotion);
                putAllPotionVariants(entries, ModItemDefinitions::getBerserkersRagePotion);
                putAllPotionVariants(entries, ModItemDefinitions::getNeptunesWakePotion);
                putAllPotionVariants(entries, ModItemDefinitions::getParalysisPotion);
            })
            .build();

    public static void putAllPotionVariants(ItemGroup.Entries entries, Function<Item, ItemStack> potionFunction) {
        entries.add(potionFunction.apply(Items.POTION));
        entries.add(potionFunction.apply(Items.SPLASH_POTION));
        entries.add(potionFunction.apply(Items.LINGERING_POTION));
    }
}
