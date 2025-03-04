package net.eclipticcosmos.thaumatics;

import com.mojang.logging.LogUtils;
import net.eclipticcosmos.thaumatics.block.ModBlocks;
import net.eclipticcosmos.thaumatics.item.ModCreativeModTabs;
import net.eclipticcosmos.thaumatics.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ThaumaticStep.MOD_ID)
public class ThaumaticStep
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "thaumatics";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public ThaumaticStep()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);


    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }



    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            //event.accept(ModItems.ARCANEGEAR);
            //event.accept(ModItems.DARKGEAR);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void registerOverrides(FMLClientSetupEvent event)
        {
            System.out.println("Registering item property overrides...");
            ItemProperties.register(ModItems.SPELLSCROLL.get(), new ResourceLocation("element_type"),
                    (stack, level, entity, seed) -> {
                        CompoundTag tag = stack.getTag();
                        String element = "none";
                        if (tag != null) {
                            element = tag.getString("Element");
                            return switch (element) {
                                case "Arcane" -> 1.0f;
                                case "Abysmal" -> 2.0f;
                                case "Aviation" -> 3.0f;
                                default -> 0.0f;
                            };
                        } else {
                            return 0.0f;
                        }

                    });
        }
    }
}
