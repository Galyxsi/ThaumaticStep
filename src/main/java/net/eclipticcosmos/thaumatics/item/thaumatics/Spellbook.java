package net.eclipticcosmos.thaumatics.item.thaumatics;

import net.eclipticcosmos.thaumatics.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


import java.util.*;


public class Spellbook extends Item {




    private static final String TAG_ITEMS = "spellScrolls";
    public static int MAX_STORAGE = 8;
    private static final int BAR_COLOR = Mth.color(0.7f,0.7f,0.7f);

    public static String spellbookName = "";
    public static ModItems.spellbookTiers spellbookTier = ModItems.spellbookTiers.NULL;



    public int CURRENT_STORAGE = 0;

    public Spellbook(Properties pProperties) {
        super(pProperties);
    }

    public List<ItemStack> getStoredSpells(ItemStack itemstack) {
        CompoundTag tag = itemstack.getOrCreateTag();
        ListTag spellsTag = tag.getList("Spells", Tag.TAG_COMPOUND);

        List<ItemStack> spells = new ArrayList<>();
        for (int i = 0; i < spellsTag.size(); i++) {
            CompoundTag spellTag = spellsTag.getCompound(i);
            if (spellTag != null) {
                spells.add(ItemStack.of(spellTag));
            }
        }
        return spells;
    }

    public boolean addSpell(ItemStack bookStack, ItemStack spell) {
        List<ItemStack> storedSpells = getStoredSpells(bookStack);
        if (storedSpells.size() >= MAX_STORAGE) {
            return false; // Book is full
        } else {
            CompoundTag tag = bookStack.getOrCreateTag();
            ListTag spellsTag = tag.getList("Spells", Tag.TAG_COMPOUND);
            spellsTag.add(spell.save(new CompoundTag()));
            tag.put("Spells", spellsTag);
            return true;
        }
    }

    public ItemStack removeSpell(ItemStack bookStack) {
        CompoundTag tag = bookStack.getOrCreateTag();
        ListTag spellsTag = tag.getList("Spells", Tag.TAG_COMPOUND);
        if (spellsTag.isEmpty()) {
            return ItemStack.EMPTY;
        }
        CompoundTag removedTag = (CompoundTag) spellsTag.remove(spellsTag.size() - 1);
        tag.put("Spells", spellsTag);
        return ItemStack.of(removedTag);
    }


    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return false;
    }

    public boolean overrideStackedOnOther(ItemStack bookStack, Slot slot, ClickAction action, Player player) {
        if(bookStack.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemOn = slot.getItem();
            if (itemOn.isEmpty()) {
                slot.set(removeSpell(bookStack));
            } else {
                if (itemOn.getItem() == ModItems.SPELLSCROLL.get())
                {
                    if (addSpell(bookStack, slot.getItem())) {
                        slot.getItem().shrink(1);
                    }

                }
            }
            return true;
        }
    }



    public boolean isBarVisible(ItemStack pStack) {
        List<ItemStack> storedSpells = getStoredSpells(pStack);
        if (!storedSpells.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        List<ItemStack> storedSpells = getStoredSpells(pStack);
        return 12 * storedSpells.size() / MAX_STORAGE;
    }

    @Override
    public int getBarColor(ItemStack pStack) {return BAR_COLOR; }

    public static ItemStack createSpellbook(int max, String name, ModItems.spellbookTiers tier) {
        ItemStack stack = new ItemStack(ModItems.SPELLBOOK.get());
        CompoundTag tag = new CompoundTag();
        stack.setTag(tag);
        MAX_STORAGE = max;
        spellbookName = name;
        spellbookTier = tier;
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        List<ItemStack> spells = getStoredSpells(pStack);
        if (spells.isEmpty()) {
            pTooltipComponents.add(Component.translatable("item.thaumatics.spellbook.emptyText").withStyle(ChatFormatting.GRAY));
        } else {
            for (ItemStack spell : spells) {
                CompoundTag spellTag = spell.getTag();
                if (spellTag == null) {
                    pTooltipComponents.add(Component.translatable("item.thaumatics.spellscroll.none").withStyle(ChatFormatting.AQUA));
                } else {
                    String spellName = spellTag.getString("Spell");
                    pTooltipComponents.add(Component.translatable("item.thaumatics.spellscroll." + spellName).withStyle(ChatFormatting.AQUA));
                }

            }
        }
    }

    

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable("item.thaumatics.spellbook.tier." + spellbookTier.getElementString() + "." + spellbookTier.getName());
    }

}
