package grauly.anaramus;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import grauly.anaramus.blocks.potioncauldron.PotionCauldron;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class Anaramus implements ModInitializer {
    public static final String MODID = "anaramus";

    public static final Block POTION_CAULDRON = Registry.register(Registries.BLOCK, new Identifier(MODID,"potion_cauldron"), new PotionCauldron(FabricBlockSettings.create().hardness(2).requiresTool()));
    public static final Item POTION_CAULDRON_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID,"potion_cauldron"), new PolymerBlockItem(POTION_CAULDRON, new FabricItemSettings().rarity(Rarity.UNCOMMON), Items.CAULDRON));
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(POTION_CAULDRON_ITEM))
            .displayName(Text.translatable("itemGroup.anaramus.main"))
            .entries((context, entries) -> {
                entries.add(POTION_CAULDRON_ITEM);
            })
            .build();
    @Override
    public void onInitialize() {
        PolymerItemGroupUtils.registerPolymerItemGroup(new Identifier(MODID,"anaramus_content"),ITEM_GROUP);
    }
}
