package grauly.anaramus;

import grauly.anaramus.blocks.potioncauldron.PotionCauldron;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static grauly.anaramus.Anaramus.MODID;

public class ModBlocks {
    public static final Block POTION_CAULDRON = Registry.register(Registries.BLOCK, new Identifier(MODID, "potion_cauldron"), new PotionCauldron(FabricBlockSettings.create().hardness(2).requiresTool()));
}
