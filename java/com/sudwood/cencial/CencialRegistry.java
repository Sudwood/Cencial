package com.sudwood.cencial;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.sudwood.cencial.blocks.CencialBlocks;
import com.sudwood.cencial.items.CencialItems;
import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

@Mod.EventBusSubscriber
public class CencialRegistry 
{
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) 
    {
    	event.getRegistry().registerAll(CencialBlocks.BLOCKS.toArray(new Block[0]));
    	registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
    	event.getRegistry().registerAll(CencialItems.ITEMS.toArray(new Item[0]));
    }
    public static void registerTileEntities()
    {
    	GameRegistry.registerTileEntity(TileEntityBasicPlinth.class, new ResourceLocation(CencialMod.MODID, "basicplinth"));
    	
    }
    
   
}
