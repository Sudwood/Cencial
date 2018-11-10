package com.sudwood.cencial;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

import com.sudwood.cencial.blocks.CencialBlocks;
import com.sudwood.cencial.proxy.CommonProxy;

@Mod(modid = CencialMod.MODID, name = CencialMod.NAME, version = CencialMod.VERSION, dependencies = "", useMetadata = true)
public class CencialMod
{
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
    public static CommonProxy proxy;

    @Mod.Instance
    public static CencialMod instance;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
