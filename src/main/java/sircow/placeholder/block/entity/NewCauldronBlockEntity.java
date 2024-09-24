package sircow.placeholder.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import sircow.placeholder.item.ModItems;
import sircow.placeholder.screen.NewCauldronBlockScreenHandler;

public class NewCauldronBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT_TWO = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    protected final PropertyDelegate propertyDelegateTwo;
    private int progress = 0;
    private int maxProgress = 100;
    private int progressWater = 0;
    private int maxWaterProgress = 64;

    public NewCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NEW_CAULDRON_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NewCauldronBlockEntity.this.progress;
                    case 1 -> NewCauldronBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NewCauldronBlockEntity.this.progress = value;
                    case 1 -> NewCauldronBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
        this.propertyDelegateTwo = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NewCauldronBlockEntity.this.progressWater;
                    case 1 -> NewCauldronBlockEntity.this.maxWaterProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NewCauldronBlockEntity.this.progressWater = value;
                    case 1 -> NewCauldronBlockEntity.this.maxWaterProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new NewCauldronBlockData(this.pos);

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, false, registryLookup);
        nbt.putInt("newCauldronProgress", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        progress = nbt.getInt("newCauldronProgress");
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Cauldron");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NewCauldronBlockScreenHandler(syncId, playerInventory, this, this.propertyDelegate, this.propertyDelegateTwo);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        if(isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }

        insertWater();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        if (this.getStack(INPUT_SLOT).getItem() == ModItems.RAW_HIDE) {
            ItemStack result = new ItemStack(Items.LEATHER);
            this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        }
        else if (this.getStack(INPUT_SLOT).getItem() == Items.DIRT) {
            ItemStack result = new ItemStack(Items.MUD);
            this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        }
        this.removeStack(INPUT_SLOT, 1);
        this.progressWater -= 1;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private void insertWater() {
        PotionContentsComponent potionContentsComponent = getStack(INPUT_SLOT_TWO).get(DataComponentTypes.POTION_CONTENTS);
        if ((getStack(INPUT_SLOT_TWO).getItem() == Items.WATER_BUCKET)
                && ((this.progressWater != this.maxWaterProgress)
                || (this.progressWater + 16 < this.maxWaterProgress))) {
            ItemStack emptyBucket = new ItemStack(Items.BUCKET);
            this.progressWater += 16;
            this.removeStack(INPUT_SLOT_TWO, 1);
            this.setStack(INPUT_SLOT_TWO, new ItemStack(emptyBucket.getItem()));
        }
        else if ((getStack(INPUT_SLOT_TWO).getItem() == Items.POTION && (potionContentsComponent != null && potionContentsComponent.matches(Potions.WATER)))
                && ((this.progressWater != this.maxWaterProgress)
                || (this.progressWater + (getStack(INPUT_SLOT_TWO).getCount() * 4) < this.maxWaterProgress))) {
            int stackSize = getStack(INPUT_SLOT_TWO).getCount();
            this.progressWater += stackSize * 4;
            ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);
            this.removeStack(INPUT_SLOT_TWO, 1);
            this.setStack(INPUT_SLOT_TWO, new ItemStack(emptyBottle.getItem()));
        }

        if (this.progressWater > this.maxWaterProgress){
            this.progressWater = 100;
        }
    }

    private boolean hasRecipe() {
        if (this.getStack(INPUT_SLOT).getItem() == ModItems.RAW_HIDE) {
            ItemStack result = new ItemStack(Items.LEATHER);
            boolean hasInput = getStack(INPUT_SLOT).getItem() == ModItems.RAW_HIDE;
            return hasInput && this.progressWater >= 1 && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
        }
        else if (this.getStack(INPUT_SLOT).getItem() == Items.DIRT) {
            ItemStack result = new ItemStack(Items.MUD);
            boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.DIRT;
            return hasInput && this.progressWater >= 1 && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
        }
        return false;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
