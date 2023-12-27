package grauly.anaramus.blocks.potioncauldron;

import grauly.anaramus.ModBlockEntities;
import grauly.anaramus.ModTags;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipe;
import grauly.anaramus.recipes.cauldron.CauldronBrewingRecipeType;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class PotionCauldronBlockEntity extends BlockEntity {

    private static final int INVENTORY_SIZE = 27;
    DefaultedList<ItemStack> cauldronInventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    public PotionCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POTION_CAULDRON_BLOCK_ENTITY_TYPE, pos, state);
    }

    /**
     * Brings an item into the cauldron, if it cannot be fully consumed, it will modify the passed stack
     * This will only partially stack, so that the same ingredient can be added to, but previous ones are locked in
     *
     * @param stack the stack that is trying to get into the cauldron
     * @return true if stack was fully inserted
     */
    public boolean insertItem(ItemStack stack) {
        if (stack.isIn(ModTags.CAULDRON_BLACKLIST)) return false;

        ItemStack lastStack = ItemStack.EMPTY;
        int lastItemIndex = 0;
        //get last used itemslot
        for (int i = 0; i < cauldronInventory.size(); i++) {
            if (cauldronInventory.get(i).isEmpty()) {
                break;
            }
            lastStack = cauldronInventory.get(i);
            lastItemIndex = i;
        }
        //base case, inventory is empty
        if (lastStack.isEmpty()) {
            cauldronInventory.set(lastItemIndex, stack.copy());
            this.markDirty();
            return true;
        }
        //assume that stacking can happen
        if (lastStack.isOf(stack.getItem())) {
            int missing = lastStack.getMaxCount() - lastStack.getCount();
            if (stack.getCount() < missing) {
                //stack from the getgo
                lastStack.setCount(lastStack.getCount() + stack.getCount());
                return true;
            } else {
                //stack and overflow
                int leftOver = stack.getCount() - missing;
                lastStack.setCount(lastStack.getMaxCount());
                stack.setCount(leftOver);
                this.markDirty();
                if (lastItemIndex + 1 < INVENTORY_SIZE) {
                    //overflow to next stack
                    cauldronInventory.set(lastItemIndex + 1, stack);
                    return true;
                } else {
                    //overflow to outside of cauldron
                    return false;
                }
            }
        }
        //cannot stack
        if (lastItemIndex + 1 < INVENTORY_SIZE) {
            cauldronInventory.set(lastItemIndex + 1, stack);
            this.markDirty();
            return true;
        }
        return false;
    }

    /**
     * attempts to craft a recipe
     *
     * @param activationItem the item the craft was activated with
     * @return the result ItemStack, or null if no craft succeeded
     */
    public ItemStack craft(ItemStack activationItem) {
        //check  if on server
        if (getWorld() == null || getWorld().isClient()) return null;
        //check if cauldron is full
        if (this.getCachedState().get(PotionCauldron.FLUID_LEVEL) != 3) return null;

        BlockPos checkPos = this.getPos().down();
        boolean hasHeat = getWorld().getBlockState(checkPos).isIn(ModTags.HEAT_SOURCES);
        SimpleInventory craftingInventory = new SimpleInventory(cauldronInventory.toArray(ItemStack[]::new));
        var foundRecipe = getWorld().getRecipeManager().getFirstMatch(CauldronBrewingRecipeType.INSTANCE, craftingInventory, getWorld());

        if (foundRecipe.isEmpty()) return null;

        CauldronBrewingRecipe recipe = foundRecipe.get().value();

        if (recipe.needsFire() && !hasHeat) return null;

        if (recipe.getActivationIngredient().test(activationItem)) {
            return recipe.craft(craftingInventory, getWorld().getRegistryManager());
        }
        return null;
    }

    public void invalidateCraft() {
        cauldronInventory.clear();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        cauldronInventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
        Inventories.readNbt(nbt, cauldronInventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, cauldronInventory, false);
    }
}
