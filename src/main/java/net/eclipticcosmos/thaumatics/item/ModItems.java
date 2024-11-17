package net.eclipticcosmos.thaumatics.item;

import net.eclipticcosmos.thaumatics.ThaumaticStep;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThaumaticStep.MOD_ID);

    public static final RegistryObject<Item> ARCANEGEAR = ITEMS.register("arcanegear",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARKGEAR = ITEMS.register("darkgear",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SKYGEAR = ITEMS.register("skygear",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LUNARDUST = ITEMS.register("lunardust",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
