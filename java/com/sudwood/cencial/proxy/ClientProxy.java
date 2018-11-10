package com.sudwood.cencial.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.sudwood.cencial.blocks.CencialBlocks;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends ServerProxy{
   
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) 
    {
    	
    	CencialBlocks.initModels();
    }
    
    
}
