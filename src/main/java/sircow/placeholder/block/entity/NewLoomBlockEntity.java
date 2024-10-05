package sircow.placeholder.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
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
import sircow.placeholder.screen.NewLoomBlockScreenHandler;

import java.util.HashMap;
import java.util.Map;

public class NewLoomBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT_TWO = 1;
    private static final int INPUT_SLOT_THREE = 2;
    private static final int OUTPUT_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 10;

    // define recipes
    private static final Map<Item, Item> conversionMap = new HashMap<>();
    static {
        conversionMap.put(Items.BLACK_WOOL, ModItems.BLACK_CLOTH);
        conversionMap.put(Items.BLUE_WOOL, ModItems.BLUE_CLOTH);
        conversionMap.put(Items.BROWN_WOOL, ModItems.BROWN_CLOTH);
        conversionMap.put(Items.CYAN_WOOL, ModItems.CYAN_CLOTH);
        conversionMap.put(Items.GRAY_WOOL, ModItems.GRAY_CLOTH);
        conversionMap.put(Items.GREEN_WOOL, ModItems.GREEN_CLOTH);
        conversionMap.put(Items.LIGHT_BLUE_WOOL, ModItems.LIGHT_BLUE_CLOTH);
        conversionMap.put(Items.LIGHT_GRAY_WOOL, ModItems.LIGHT_GRAY_CLOTH);
        conversionMap.put(Items.LIME_WOOL, ModItems.LIME_CLOTH);
        conversionMap.put(Items.MAGENTA_WOOL, ModItems.MAGENTA_CLOTH);
        conversionMap.put(Items.ORANGE_WOOL, ModItems.ORANGE_CLOTH);
        conversionMap.put(Items.PINK_WOOL, ModItems.PINK_CLOTH);
        conversionMap.put(Items.PURPLE_WOOL, ModItems.PURPLE_CLOTH);
        conversionMap.put(Items.RED_WOOL, ModItems.RED_CLOTH);
        conversionMap.put(Items.WHITE_WOOL, ModItems.WHITE_CLOTH);
        conversionMap.put(Items.YELLOW_WOOL, ModItems.YELLOW_CLOTH);
        conversionMap.put(ModItems.PHANTOM_SINEW, ModItems.HOLLOW_TWINE);
    }

    public NewLoomBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NEW_LOOM_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NewLoomBlockEntity.this.progress;
                    case 1 -> NewLoomBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NewLoomBlockEntity.this.progress = value;
                    case 1 -> NewLoomBlockEntity.this.maxProgress = value;
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
        return new NewLoomBlockData(this.pos);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
        nbt.putInt("newLoomProgress", this.progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.progress = nbt.getInt("newLoomProgress");
        Inventories.readNbt(nbt, this.inventory, registryLookup);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Loom");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NewLoomBlockScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Item inputItem = this.getStack(INPUT_SLOT).getItem();
        Item inputItem2 = this.getStack(INPUT_SLOT_TWO).getItem();
        Item inputItemShears = this.getStack(INPUT_SLOT_THREE).getItem();
        Item outputItem = conversionMap.get(inputItem);

        // start crafting process if recipe is successful
        if (outputItem != null && inputItem2 == inputItem && inputItemShears == Items.SHEARS) {
            ItemStack result = new ItemStack(outputItem);
            this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
        }
        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(INPUT_SLOT_TWO, 1);
        this.getStack(INPUT_SLOT_THREE).setDamage(this.getStack(INPUT_SLOT_THREE).getDamage() + 1);
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        // check if inserted item has recipe
        Item inputItem = this.getStack(INPUT_SLOT).getItem();
        Item inputItem2 = this.getStack(INPUT_SLOT_TWO).getItem();
        Item inputItemShears = this.getStack(INPUT_SLOT_THREE).getItem();
        Item outputItem = conversionMap.get(inputItem);

        if (outputItem != null && inputItem2 == inputItem && inputItemShears == Items.SHEARS) {
            ItemStack result = new ItemStack(outputItem);
            boolean hasInput = getStack(INPUT_SLOT).getItem() == inputItem
                    && getStack(INPUT_SLOT_TWO).getItem() == inputItem2
                    && getStack(INPUT_SLOT_THREE).getItem() == Items.SHEARS;
            return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
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
