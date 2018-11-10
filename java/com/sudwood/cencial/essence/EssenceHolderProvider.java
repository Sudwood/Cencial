package com.sudwood.cencial.essence;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class EssenceHolderProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IEssenceHolder.class)
	public static final Capability<IEssenceHolder> ESS_CAP = null;
	
	private IEssenceHolder instance = ESS_CAP.getDefaultInstance();
	
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		// TODO Auto-generated method stub
		return capability == ESS_CAP;
	}

	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		// TODO Auto-generated method stub
		return capability == ESS_CAP ? ESS_CAP.<T> cast(this.instance) : null; 
	}

	public NBTBase serializeNBT() 
	{
		// TODO Auto-generated method stub
		return ESS_CAP.getStorage().writeNBT(ESS_CAP, this.instance, null); 
	}

	public void deserializeNBT(NBTBase nbt) 
	{
		// TODO Auto-generated method stub
		ESS_CAP.getStorage().readNBT(ESS_CAP, this.instance, null, nbt); 
	}

}
