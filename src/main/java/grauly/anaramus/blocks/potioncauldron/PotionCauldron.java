package grauly.anaramus.blocks.potioncauldron;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;

public class PotionCauldron extends AbstractCauldronBlock implements PolymerBlock {


    public PotionCauldron(Settings settings) {
        super(settings, CauldronBehavior.WATER_CAULDRON_BEHAVIOR);
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.CAULDRON;
    }

    @Override
    public boolean isFull(BlockState state) {
        return false;
    }
}
