package com.sudwood.cencial;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.Logger;

import com.sudwood.cencial.blocks.CencialBlocks;
import com.sudwood.cencial.essence.EssenceHolder;
import com.sudwood.cencial.essence.EssenceHolderStorage;
import com.sudwood.cencial.essence.IEssenceHolder;
import com.sudwood.cencial.proxy.ServerProxy;

@Mod(modid = CencialMod.MODID, name = CencialMod.NAME, version = CencialMod.VERSION, dependencies = "", useMetadata = true)
public class CencialMod
{
	public static Configuration config;
    public static final String MODID = "cencial";
    public static final String NAME = "Cencial Mod";
    public static final String VERSION = "0.1";

    public static Logger logger;
    
    static{
    	FluidRegistry.enableUniversalBucket();
    }
    
    public static final CreativeTabs tabCencial = (new CreativeTabs("tabCencial") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CencialBlocks.basicPlinth);
		}
		
});
    @SidedProxy(clientSide = "com.sudwood.cencial.proxy.ClientProxy", serverSide = "com.sudwood.cencial.proxy.ServerProxy")
    public static ServerProxy proxy;
    @Mod.Instance
    public static CencialMod instance;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "CencialMod.cfg"));
        Config.readConfig();
        CapabilityManager.INSTANCE.register(IEssenceHolder.class, new EssenceHolderStorage(), EssenceHolder.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(CencialMod.instance, new GuiHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
    	if (config.hasChanged()) 
    	{
            config.save();
        }
    }
    
    
    
   
}
