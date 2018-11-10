package com.sudwood.cencial.capability;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.sudwood.cencial.CencialMod;
import com.sudwood.cencial.essence.EssenceHolderProvider;
import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

@Mod.EventBusSubscriber
public class CapabilityHandler
{
	public static final ResourceLocation ESS_CAP = new ResourceLocation(CencialMod.MODID, "essence");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<TileEntity> event)
	{
		if(event.getObject() instanceof TileEntityBasicPlinth)
		{
		event.addCapability(ESS_CAP, new EssenceHolderProvider());
		
		}
	}
}
