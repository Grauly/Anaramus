package grauly.anaramus.blocks.potioncauldron;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import grauly.anaramus.Anaramus;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.block.LeveledCauldronBlock.LEVEL;

public class PotionCauldron extends Block implements PolymerBlock, BlockEntityProvider {

    public static final IntProperty FLUID_LEVEL = IntProperty.of("level",0,3);

    public PotionCauldron(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Anaramus.LOGGER.info("used");
        ItemStack handStack = player.getStackInHand(hand);
        if(world.getBlockEntity(pos) instanceof PotionCauldronBlockEntity cauldronBlockEntity) {
            var craftResult = cauldronBlockEntity.craft(handStack);
            if(craftResult != null) {
                //empty the cauldron
                emptyFluidLevel(state,world,pos);
                ItemEntity itemEntity = new ItemEntity(world,pos.getX() +0.5, pos.getY() + 0.5, pos.getZ() + 0.5, craftResult);
                world.spawnEntity(itemEntity);
                playCraftCompleteEffect(world, pos);
                return ActionResult.SUCCESS;
            }
        }
        boolean filled = false;
        if(handStack.isOf(Items.WATER_BUCKET)) {
            filled = state.get(FLUID_LEVEL) != 3;
            CauldronBehavior.fillCauldron(world,pos,player,hand,handStack,getDefaultState().with(FLUID_LEVEL,3),SoundEvents.ITEM_BUCKET_EMPTY);
        }
        if(handStack.isOf(Items.POTION) && PotionUtil.getPotion(handStack) == Potions.WATER) {
            filled = state.get(FLUID_LEVEL) != 3;
            player.setStackInHand(hand, ItemUsage.exchangeStack(handStack, player, new ItemStack(Items.GLASS_BOTTLE)));
            incrementFluidLevel(state,world,pos);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        if(filled && world.getBlockEntity(pos) instanceof PotionCauldronBlockEntity cauldronBlockEntity) {
            cauldronBlockEntity.invalidateCraft();
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        //replicate vanilla logic
        if(entity.isOnFire() && isEntityInWater(state,pos,entity)) {
            entity.extinguishWithSound();
            if(entity.canModifyAt(world, pos)) {
                decrementFluidLevel(state,world,pos);
            }
        }
        //own logic
        if(entity instanceof ItemEntity itemEntity && world.getBlockEntity(pos) instanceof PotionCauldronBlockEntity cauldronBlockEntity && isEntityInWater(state,pos,entity)) {
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AbstractCauldronBlock.OUTLINE_SHAPE;
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return AbstractCauldronBlock.RAYCAST_SHAPE;
    }

    public void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
        state.with(FLUID_LEVEL,Math.max(0,state.get(FLUID_LEVEL) -1));
        confirmBlockStateChange(state, world, pos);
    }

    public void incrementFluidLevel(BlockState state, World world, BlockPos pos) {
        state.with(FLUID_LEVEL,Math.min(3,state.get(FLUID_LEVEL) +1));
        confirmBlockStateChange(state, world, pos);
    }

    public void emptyFluidLevel(BlockState state, World world, BlockPos pos) {
        state.with(FLUID_LEVEL,0);
        confirmBlockStateChange(state, world, pos);
    }

    public void fullFluidLevel(BlockState state, World world, BlockPos pos) {
        state.with(FLUID_LEVEL,3);
        confirmBlockStateChange(state, world, pos);
    }

    protected void confirmBlockStateChange(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
    }

    protected void playIngredientAddEffect(World world, BlockPos pos) {
        world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1,1,true);
        world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1,1,true);
        world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP,pos.getX() + 0.5,pos.getY() + 1.2, pos.getZ() + 0.5,0,0.1,0);
    }

    protected void playCraftCompleteEffect(World world, BlockPos pos) {

    }

    protected boolean isEntityInWater(BlockState state, BlockPos pos, Entity entity) {
        return entity.getY() < (double)pos.getY() + this.getFluidHeight(state) && entity.getBoundingBox().maxY > (double)pos.getY() + 0.25;
    }

    protected double getFluidHeight(BlockState state) {
        return (6.0 + (double)state.get(LEVEL) * 3.0) / 16.0;
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return state.get(FLUID_LEVEL) == 0 ? Blocks.CAULDRON : Blocks.WATER_CAULDRON;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return state.get(FLUID_LEVEL) == 0 ?
                Blocks.CAULDRON.getDefaultState() :
                getPolymerBlock(state).getDefaultState().with(LEVEL,state.get(LEVEL));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PotionCauldronBlockEntity(pos,state);
    }
}
