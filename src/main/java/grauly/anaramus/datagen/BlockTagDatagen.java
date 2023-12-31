package grauly.anaramus.datagen;

import grauly.anaramus.Anaramus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static grauly.anaramus.ModBlocks.POTION_CAULDRON;
import static grauly.anaramus.ModTags.HEAT_SOURCES;

public class BlockTagDatagen extends FabricTagProvider.BlockTagProvider {
    public BlockTagDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public static final TagKey<Block> PICKAXE_MINEABLE = TagKey.of(RegistryKeys.BLOCK,new Identifier("minecraft","mineable/pickaxe"));
    public static final TagKey<Block> STONE_MININGLEVEL = TagKey.of(RegistryKeys.BLOCK,new Identifier("minecraft","needs_stone_tool"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(PICKAXE_MINEABLE)
                .add(POTION_CAULDRON);
        getOrCreateTagBuilder(STONE_MININGLEVEL)
                .add(POTION_CAULDRON);
        getOrCreateTagBuilder(HEAT_SOURCES)
                .add(Blocks.FIRE)
                .add(Blocks.SOUL_FIRE)
                .add(Blocks.CAMPFIRE)
                .add(Blocks.SOUL_CAMPFIRE)
                .add(Blocks.LAVA);
    }
}
