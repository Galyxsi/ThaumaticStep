package net.eclipticcosmos.thaumatics.item.thaumatics;

import net.eclipticcosmos.thaumatics.item.ModCreativeModTabs;
import net.eclipticcosmos.thaumatics.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.CreativeModeTabRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class SpellScrollItem extends Item {

    public SpellScrollItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return super.useOn(pContext);
    }

    @Override
    public Component getName(ItemStack pStack) {
        CompoundTag tag = pStack.getTag();
        String element = "None";
        if (tag != null) {
            element = tag.getString("Element");
        }

        return Component.translatable("item.thaumatics.spellscroll." + element);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        CompoundTag tag = pStack.getTag();
        String spell = "None";
        String element = "None";
        if (tag != null) {
            spell = tag.getString("Spell");
            element = tag.getString("Element");
        }
        MutableComponent spellName = Component.translatable("item.thaumatics.spellscroll." + spell);
        switch (element) {
            case "Arcane" -> pTooltip.add(spellName.withStyle(ChatFormatting.DARK_AQUA));
            case "Abysmal" -> pTooltip.add(spellName.withStyle(ChatFormatting.DARK_RED));
            case "Aviation" -> pTooltip.add(spellName.withStyle(ChatFormatting.DARK_GREEN));

        }


    }


    /*public static void appendHoverText(CompoundTag pCompound, List<Component> pTooltipComponents) {
        CompoundTag compoundtag = pStack.getTagElement("Spell");
        if (compoundtag != null) {
            appendHoverText(compoundtag, pTooltip);
        }



        //pTooltipComponents.add(Component.literal(spell));
    }*/

    public static ItemStack createScroll(String spell, String element) {
        ItemStack stack = new ItemStack(ModItems.SPELLSCROLL.get());
        CompoundTag tag = new CompoundTag();
        tag.putString("Spell", spell);
        tag.putString("Element", element);
        stack.setTag(tag);
        return stack;
    }


}
