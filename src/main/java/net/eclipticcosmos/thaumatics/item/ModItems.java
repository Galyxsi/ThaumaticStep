package net.eclipticcosmos.thaumatics.item;

import net.eclipticcosmos.thaumatics.ThaumaticStep;
import net.eclipticcosmos.thaumatics.item.thaumatics.ImbuedNecklaceItem;
import net.eclipticcosmos.thaumatics.item.thaumatics.PowerGearItem;
import net.eclipticcosmos.thaumatics.item.thaumatics.SpellScrollItem;
import net.eclipticcosmos.thaumatics.item.thaumatics.Spellbook;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.level.block.ComposterBlock;


import javax.lang.model.element.Element;

public class ModItems {



    public enum spellbookTiers {

        NULL(Element.NULL, "null"),

        OBSIDIA(Element.ABYSMAL, "obsidia"),
        MAGMU(Element.ABYSMAL, "magmu"),
        LAVAR(Element.ABYSMAL, "lavar"),
        WITOR(Element.ABYSMAL, "witor"),
        BLIGHT(Element.ABYSMAL, "blight"),

        PEARLGU(Element.ATARAXIS, "pearlgu"),
        ENMITE(Element.ATARAXIS, "enmite"),
        SOULSAPIEN(Element.ATARAXIS, "soulsapien"),
        CRYSTAL(Element.ATARAXIS, "crystal"),
        DOMINION(Element.ATARAXIS, "dominion"),

        EMISSARY(Element.ARDOR, "emissary"),
        DWELLING(Element.ARDOR, "dwelling"),
        ANIMUS(Element.ARDOR, "animus"),
        INDUS(Element.ARDOR, "indus"),
        LARYNX(Element.ARDOR, "larynx");

            private final Element element;
            private final String name;

            spellbookTiers(Element element, String name) {
                this.element = element;
                this.name = name;
            }

        public String getName() {
            return name;
        }

        public String getElementString() {
            if (element == Element.ARDOR) {
                return "ardor";
            } else if (element == Element.ABYSMAL) {
                return "abysmal";
            } else if (element == Element.ATARAXIS) {
                return "ataraxis";
            } else if (element == Element.NULL) {
                return "null";
            } else {
                return "";
            }
        }



            public enum Element {
                ABYSMAL, ATARAXIS, ARDOR, NULL
            }
    }

    public static spellbookTiers elemNum(int num) {
        switch (num) {
            case (1):
                return spellbookTiers.OBSIDIA;
            case (2):
                return spellbookTiers.MAGMU;
            case (3):
                return spellbookTiers.LAVAR;
            case (4):
                return spellbookTiers.WITOR;
            case (5):
                return spellbookTiers.BLIGHT;
            case (6):
                return spellbookTiers.PEARLGU;
            case (7):
                return spellbookTiers.ENMITE;
            case (8):
                return spellbookTiers.SOULSAPIEN;
            case (9):
                return spellbookTiers.CRYSTAL;
            case (10):
                return spellbookTiers.DOMINION;
            case (11):
                return spellbookTiers.EMISSARY;
            case (12):
                return spellbookTiers.DWELLING;
            case (13):
                return spellbookTiers.ANIMUS;
            case (14):
                return spellbookTiers.INDUS;
            case (15):
                return spellbookTiers.LARYNX;
            default:
                return spellbookTiers.NULL;
        }
    }


    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThaumaticStep.MOD_ID);

    public static final RegistryObject<Item> ARCANEGEAR = ITEMS.register("arcanegear",
            () -> new PowerGearItem(new Item.Properties()));

    public static final RegistryObject<Item> DARKGEAR = ITEMS.register("darkgear",
            () -> new PowerGearItem(new Item.Properties()));

    public static final RegistryObject<Item> SKYGEAR = ITEMS.register("skygear",
            () -> new PowerGearItem(new Item.Properties()));

    public static final RegistryObject<Item> VOIDGEAR = ITEMS.register("voidgear",
            () -> new PowerGearItem(new Item.Properties()));

    public static final RegistryObject<Item> LUNARDUST = ITEMS.register("lunardust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPELLSCROLL = ITEMS.register("spellscroll",
            () -> new SpellScrollItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SPELLBOOK = ITEMS.register("spellbook",
            () -> new Spellbook(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> NECKLACE = ITEMS.register("necklace",
            () -> new ImbuedNecklaceItem());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }




}
