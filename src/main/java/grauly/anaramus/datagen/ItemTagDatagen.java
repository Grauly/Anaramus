package grauly.anaramus.datagen;

import grauly.anaramus.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagDatagen extends FabricTagProvider.ItemTagProvider {

    public ItemTagDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.CAULDRON_BLACKLIST)
                .addOptionalTag(ItemTags.TRIMMABLE_ARMOR)
                .addOptionalTag(ItemTags.SWORDS)
                .addOptionalTag(ItemTags.AXES)
                .addOptionalTag(ItemTags.HOES)
                .addOptionalTag(ItemTags.PICKAXES)
                .addOptionalTag(ItemTags.SHOVELS)
                .add(Items.ELYTRA)
                .add(Items.BOW)
                .add(Items.TRIDENT)
                .add(Items.SHIELD);

        getOrCreateTagBuilder(ModTags.ALIVE_CORALS)
                .add(Items.BRAIN_CORAL_FAN)
                .add(Items.BUBBLE_CORAL_FAN)
                .add(Items.FIRE_CORAL_FAN)
                .add(Items.TUBE_CORAL_FAN)
                .add(Items.HORN_CORAL_FAN)
                .add(Items.BRAIN_CORAL)
                .add(Items.BUBBLE_CORAL)
                .add(Items.FIRE_CORAL)
                .add(Items.TUBE_CORAL)
                .add(Items.HORN_CORAL)
                .add(Items.BRAIN_CORAL_BLOCK)
                .add(Items.BUBBLE_CORAL_BLOCK)
                .add(Items.FIRE_CORAL_BLOCK)
                .add(Items.TUBE_CORAL_BLOCK)
                .add(Items.HORN_CORAL_BLOCK);
    }
}
