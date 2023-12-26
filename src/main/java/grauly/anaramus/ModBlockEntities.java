package grauly.anaramus;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import grauly.anaramus.blocks.potioncauldron.PotionCauldronBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON_BLOCK_ENTITY_TYPE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("tutorial", "demo_block_entity"),
            FabricBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new, ModBlocks.POTION_CAULDRON).build()
    );

    public static void setup() {
        PolymerBlockUtils.registerBlockEntity(POTION_CAULDRON_BLOCK_ENTITY_TYPE);
    }
}
