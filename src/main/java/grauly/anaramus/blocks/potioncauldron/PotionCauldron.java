package grauly.anaramus.blocks.potioncauldron;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import grauly.anaramus.Anaramus;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.particle.BubbleColumnUpParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PotionCauldron extends LeveledCauldronBlock implements PolymerBlock, BlockEntityProvider {

    public PotionCauldron(Settings settings) {
        super(settings, LeveledCauldronBlock.RAIN_PREDICATE, CauldronBehavior.WATER_CAULDRON_BEHAVIOR);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Anaramus.LOGGER.info("used");
        ItemStack handStack = player.getStackInHand(hand);
        if(world.getBlockEntity(pos) instanceof PotionCauldronBlockEntity cauldronBlockEntity) {
            var craftResult = cauldronBlockEntity.craft(handStack);
            if(craftResult != null) {
                //empty the cauldron
                for (int i = 0; i < 3; i++) {
                    decrementFluidLevel(state,world,pos);
                }
                ItemEntity itemEntity = new ItemEntity(world,pos.getX() +0.5, pos.getY() + 0.5, pos.getZ() + 0.5, craftResult);
                world.spawnEntity(itemEntity);
                playCraftCompleteEffect(world, pos);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    protected void playIngredientAddEffect(World world, BlockPos pos) {
        world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1,1,true);
        world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1,1,true);
        world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP,pos.getX() + 0.5,pos.getY() + 1.2, pos.getZ() + 0.5,0,0.1,0);
    }

    protected void playCraftCompleteEffect(World world, BlockPos pos) {

    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        Anaramus.LOGGER.info("collision found");
        if(entity instanceof ItemEntity itemEntity && world.getBlockEntity(pos) instanceof PotionCauldronBlockEntity cauldronBlockEntity) {
            Anaramus.LOGGER.info("correct collision found");
            ItemStack stack = itemEntity.getStack();
            boolean consumed = cauldronBlockEntity.insertItem(stack);
            if (consumed) {
                itemEntity.kill();
            } else {
                itemEntity.setStack(stack);
            }
            playIngredientAddEffect(world, pos);
        }
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.WATER_CAULDRON;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return getPolymerBlock(state).getDefaultState()
                .with(LEVEL,state.get(LEVEL));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PotionCauldronBlockEntity(pos,state);
    }
}
