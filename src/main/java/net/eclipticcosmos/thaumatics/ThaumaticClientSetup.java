package net.eclipticcosmos.thaumatics;

import net.eclipticcosmos.thaumatics.item.ModItems;
import net.eclipticcosmos.thaumatics.item.thaumatics.ImbuedNecklaceItem;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ThaumaticStep.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ThaumaticClientSetup {
    @SubscribeEvent
    public static void RegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, i) -> {
            if (i == 1 && itemStack.getItem() instanceof ImbuedNecklaceItem necklaceItem) return necklaceItem.getColorEffect(itemStack);
            return -1;
        }, ModItems.NECKLACE.get());
    }
}
