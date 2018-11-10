package com.sudwood.cencial.proxy;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.sudwood.cencial.CencialMod;
import com.sudwood.cencial.Config;
import com.sudwood.cencial.GuiHandler;
import com.sudwood.cencial.blocks.CencialBlocks;
import com.sudwood.cencial.capability.CapabilityHandler;
import com.sudwood.cencial.essence.EssenceHolder;
import com.sudwood.cencial.essence.EssenceHolderProvider;
import com.sudwood.cencial.essence.EssenceHolderStorage;
import com.sudwood.cencial.essence.IEssenceHolder;
import com.sudwood.cencial.items.CencialItems;
import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

@Mod.EventBusSubscriber
public class CommonProxy {
	  public static Configuration config;
    public void preInit(FMLPreInitializationEvent e) 
    {
    	
    	File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "CencialMod.cfg"));
        Config.readConfig();
        CapabilityManager.INSTANCE.register(IEssenceHolder.class, new EssenceHolderStorage(), EssenceHolder.class);
    	registerTileEntities();
    }

    public void init(FMLInitializationEvent e) 
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(CencialMod.instance, new GuiHandler());
    	
    }

    public void postInit(FMLPostInitializationEvent e) 
    {
    	if (config.hasChanged()) 
    	{
            config.save();
        }
    }


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) 
    {
    	event.getRegistry().registerAll(CencialBlocks.BLOCKS.toArray(new Block[0]));
    	
	/*	TileEntityHandler.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCopperChest.class, new RenderCopperChest());*/
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
    	event.getRegistry().registerAll(CencialItems.ITEMS.toArray(new Item[0]));
    }
    
    public static void registerTileEntities()
    {
    	GameRegistry.registerTileEntity(TileEntityBasicPlinth.class, CencialMod.MODID+"_basicplinth");
    	
    }


    
}
