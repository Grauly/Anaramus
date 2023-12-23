package grauly.anaramus.datagen;

import grauly.anaramus.Anaramus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BlockTagDatagen extends FabricTagProvider.BlockTagProvider {
    public BlockTagDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public static final TagKey<Block> PICKAXE_MINEABLE = TagKey.of(RegistryKeys.BLOCK,new Identifier("minecraft","mineable/pickaxe"));
    public static final TagKey<Block> STONE_MININGLEVEL = TagKey.of(RegistryKeys.BLOCK,new Identifier("minecraft","needs_stone_tool"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(PICKAXE_MINEABLE)
                .add(Anaramus.POTION_CAULDRON);
        getOrCreateTagBuilder(STONE_MININGLEVEL)
                .add(Anaramus.POTION_CAULDRON);
    }
}
