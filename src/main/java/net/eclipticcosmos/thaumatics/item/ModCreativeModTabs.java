package net.eclipticcosmos.thaumatics.item;

import net.eclipticcosmos.thaumatics.ThaumaticStep;
import net.eclipticcosmos.thaumatics.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThaumaticStep.MOD_ID);

    public static final RegistryObject<CreativeModeTab> THAUMATICS_TAB = CREATIVE_MODE_TABS.register("thaumatics", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ARCANEGEAR.get()))
            .title(Component.translatable("creativetab.thaumatics"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.ARCANEGEAR.get());
                pOutput.accept(ModItems.DARKGEAR.get());
                pOutput.accept(ModItems.SKYGEAR.get());
                pOutput.accept(ModItems.LUNARDUST.get());

                pOutput.accept(ModBlocks.LUNARSTONE.get());
            })
            .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
