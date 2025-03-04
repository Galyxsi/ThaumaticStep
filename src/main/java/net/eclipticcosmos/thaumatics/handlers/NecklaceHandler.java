package net.eclipticcosmos.thaumatics.handlers;

import net.eclipticcosmos.thaumatics.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class NecklaceHandler {
    public static ItemStack createNecklace(MobEffect effect) {
        ItemStack stack = new ItemStack(ModItems.NECKLACE.get());
        if (effect != null) {
            ResourceLocation location = ForgeRegistries.MOB_EFFECTS.getKey(effect);
            CompoundTag tag = new CompoundTag();
            tag.putString("effect", (location.getNamespace() + ":" + location.getPath()));
            tag.putBoolean("enabled",true);
            tag.putBoolean("lastEnabled",false);
            tag.putInt("amplifier", 0);
            stack.setTag(tag);

        }
        return stack;
    }

    public static Holder<MobEffect> getEffect(ItemStack itemStack) {
        if (itemStack.getTag() != null)
        {
            String value = itemStack.getTag().getString("effect");
            if (value != null && ForgeRegistries.MOB_EFFECTS.getHolder(ResourceLocation.tryParse(value)).isPresent())
            {
                return ForgeRegistries.MOB_EFFECTS.getHolder(ResourceLocation.tryParse(value)).get();
            }
        }

        return null;
    }
}
