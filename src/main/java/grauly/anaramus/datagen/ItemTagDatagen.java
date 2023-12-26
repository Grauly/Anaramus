package grauly.anaramus.datagen;

import grauly.anaramus.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagDatagen extends FabricTagProvider.ItemTagProvider {

    public ItemTagDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.CAULDRON_BLACKLIST)
                .addTag(ItemTags.TRIMMABLE_ARMOR)
                .addTag(ItemTags.SWORDS)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.HOES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS)
                .add(Items.ELYTRA)
                .add(Items.BOW)
                .add(Items.CROSSBOW)
                .add(Items.TRIDENT)
                .add(Items.SHIELD);
    }
}
