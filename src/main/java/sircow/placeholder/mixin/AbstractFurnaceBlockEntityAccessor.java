package sircow.placeholder.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbstractFurnaceBlockEntityAccessor {
    @Accessor("cookTimeTotal")
    int getCookTimeTotal();;

    @Accessor("cookTime")
    int getCookTime();

    @Accessor("cookTime")
    void setCookTime(int cookTime);

    @Accessor("burnTime")
    int getBurnTime();

    @Accessor("burnTime")
    void setBurnTime(int burnTime);

    @Invoker("isBurning")
    boolean invokeIsBurning();
}