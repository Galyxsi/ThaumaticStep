package net.eclipticcosmos.thaumatics.item.thaumatics;

import net.eclipticcosmos.thaumatics.handlers.NecklaceHandler;
import net.eclipticcosmos.thaumatics.interfaces.NecklaceInterface;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;


public class ImbuedNecklaceItem extends Item implements ICurioItem, NecklaceInterface {
    //public ImbuedNecklaceItem(Properties pProperties) { super(pProperties); }

    public ImbuedNecklaceItem() {
        super(new Item.Properties().stacksTo(1).defaultDurability(0));
    }

    public Holder<MobEffect> getMobEffect(ItemStack itemStack) {
        return NecklaceHandler.getEffect(itemStack);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        CompoundTag tag = pStack.getTag();
        String effect = "None";

        if (tag != null) {
            effect = tag.getString("effect");
        }
        //MutableComponent effectName = Component.translatable("item.thaumatics.necklace");
        MutableComponent effectName;

        if (!effect.equals("None"))
        {
            Holder<MobEffect> mobEffect = getMobEffect(pStack);
            try {
                effectName = Component.translatable("").append(mobEffect.value().getDisplayName().getString()).withStyle(mobEffect.value().getCategory().getTooltipFormatting());
            } catch (Exception e) {
                effectName = Component.translatable("item.thaumatics.necklace.noeffect");
            }

        } else {
            effectName = Component.translatable("item.thaumatics.necklace.noeffect");
        }

        pTooltip.add(effectName);

    }

    public int getColorEffect(ItemStack itemStack)
    {
        int result = -1;
        if (getMobEffect(itemStack) != null)
        {
            Color color = new Color(getMobEffect(itemStack).value().getColor());
            result = color.getRGB();
            //result = FastColor.ARGB32.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        }
        return result;
    }




    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (getMobEffect(stack) != null)
        {
            if (stack.getTag().getBoolean("enabled")) {
                MobEffectInstance instance = new MobEffectInstance(getMobEffect(stack).get(), -1, stack.getTag().getInt("amplifier"), false, false, false);
                slotContext.entity().addEffect(instance);
            }
        }
        if (stack.getTag().getBoolean("lastEnabled") != stack.getTag().getBoolean("enabled"))
        {

            tag.putBoolean("lastEnabled", stack.getTag().getBoolean("enabled"));
            if (!tag.getBoolean("enabled"))
            {
                if (slotContext.entity().hasEffect(getMobEffect(stack).get())) {
                    slotContext.entity().removeEffect(getMobEffect(stack).get());
                }
            }
            stack.setTag(tag);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        if (getMobEffect(stack) != null) {
            if (slotContext.entity().hasEffect(getMobEffect(stack).get())) {
                slotContext.entity().removeEffect(getMobEffect(stack).get());
            }
        }

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        /*CompoundTag tag = stack.getTag();
        tag.putBoolean("enabled", true);
        tag.putBoolean("lastEnabled", true);
        stack.setTag(tag);*/

        //slotContext.entity().setHealth(slotContext.entity().getHealth() - 1);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void setActive(boolean active, ItemStack stack)
    {
        CompoundTag tag = stack.getTag();
        tag.putBoolean("enabled", active);
    }

    @Override
    public void setAmplifier(int amplifier, ItemStack stack)
    {
        CompoundTag tag = stack.getTag();
        tag.putInt("amplifier", amplifier);
    }

}
