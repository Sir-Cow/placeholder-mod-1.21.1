package sircow.placeholder.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import sircow.placeholder.block.entity.NewCauldronBlockData;
import sircow.placeholder.block.entity.NewCauldronBlockEntity;

public class NewCauldronBlockScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    private final PropertyDelegate propertyDelegateTwo;
    public final NewCauldronBlockEntity blockEntity;

    public NewCauldronBlockScreenHandler(int syncId, PlayerInventory inventory, NewCauldronBlockData data) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(data.pos()),
                new ArrayPropertyDelegate(2), new ArrayPropertyDelegate(2));
    }

    public NewCauldronBlockScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate, PropertyDelegate arrayPropertyDelegateTwo) {
        super(ModScreenHandlers.NEW_CAULDRON_BLOCK_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.propertyDelegateTwo = arrayPropertyDelegateTwo;
        this.blockEntity = ((NewCauldronBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 80, 15)); // item input
        this.addSlot(new Slot(inventory, 1, 152, 52)); // water input
        this.addSlot(new Slot(inventory, 2, 80, 52)); // item output

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
        addProperties(arrayPropertyDelegateTwo);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgressArrow() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 15;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledProgressWater() {
        int waterProgress = this.propertyDelegateTwo.get(0);
        int maxWaterProgress = this.propertyDelegateTwo.get(1);  // Max Progress
        int progressWaterSize = 32;

        return maxWaterProgress != 0 && waterProgress != 0 ? waterProgress * progressWaterSize / maxWaterProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
