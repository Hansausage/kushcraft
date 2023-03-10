package dev.kushcraftdev.kushcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


import org.apache.logging.log4j.Logger;

import dev.kushcraftdev.kushcraft.common.CreativeTabKushcraft;
import dev.kushcraftdev.kushcraft.init.BlockInit;

@Mod(modid = KushCraft.MODID, name = KushCraft.NAME, version = KushCraft.VERSION)
public class KushCraft
{
    public static final String MODID = "kushcraft";
    public static final String NAME = "KushCraft";
    public static final String VERSION = "1.0";

    private static Logger logger;

    public static final CreativeTabs KUSHCRAFT_CREATIVE_TAB = new CreativeTabKushcraft("kushcraft_creative_tab");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("DUDE WEED LMAO >> {}", BlockInit.BLOCK_CANNABIS_CROP.getRegistryName());
    }
}
