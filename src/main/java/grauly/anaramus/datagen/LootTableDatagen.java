package grauly.anaramus.datagen;

import grauly.anaramus.Anaramus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import static grauly.anaramus.ModBlocks.POTION_CAULDRON;
import static grauly.anaramus.ModItems.POTION_CAULDRON_ITEM;

public class LootTableDatagen extends FabricBlockLootTableProvider {
    protected LootTableDatagen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(POTION_CAULDRON, drops(POTION_CAULDRON_ITEM));
    }
}
