package net.eclipticcosmos.thaumatics.interfaces;

import net.minecraft.world.item.ItemStack;

public interface NecklaceInterface {
    void setActive(boolean active, ItemStack stack);

    void setAmplifier(int amplifier, ItemStack stack);
}
