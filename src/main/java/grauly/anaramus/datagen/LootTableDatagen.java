package grauly.anaramus.datagen;

import grauly.anaramus.Anaramus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.loot.FabricBlockLootTableGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class LootTableDatagen extends FabricBlockLootTableProvider {
    protected LootTableDatagen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(Anaramus.POTION_CAULDRON, drops(Anaramus.POTION_CAULDRON_ITEM));
    }
}
