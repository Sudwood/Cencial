package com.sudwood.cencial.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.sudwood.cencial.CencialMod;
import com.sudwood.cencial.blocks.CencialBlocks;
import com.sudwood.cencial.proxy.CommonProxy;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        OBJLoader.INSTANCE.addDomain(CencialMod.MODID);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) 
    {
    	CencialBlocks.initModels();
    }
}
