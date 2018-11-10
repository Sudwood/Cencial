package com.sudwood.cencial.essence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EssenceHolderStorage implements IStorage<IEssenceHolder>
{

	public NBTBase writeNBT(Capability<IEssenceHolder> capability,	IEssenceHolder instance, EnumFacing side) 
	{
		NBTTagCompound holder = new NBTTagCompound();
		HashMap temp = instance.getEssence();
		Iterator entries = temp.entrySet().iterator();
		int numEssences = 0;
		while (entries.hasNext()) {
		    numEssences++;
		}
		int[] enu = new int[numEssences];
		int[] quant = new int[numEssences];
		entries = temp.entrySet().iterator();
		int iter =0;
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    EnumEssence en = (EnumEssence)entry.getKey();
		    Essence ess = (Essence)entry.getValue();
		    enu[iter] = en.Value;
		    quant[iter]=ess.getNum();
		    iter++;
		}
		holder.setIntArray("essType", enu);
		holder.setIntArray("essQuant", quant);
		holder.setInteger("maxEss", instance.maxEss);
		return holder;
	}

	public void readNBT(Capability<IEssenceHolder> capability,IEssenceHolder instance, EnumFacing side, NBTBase nbt) 
	{
		NBTTagCompound tag = (NBTTagCompound) nbt;
		
		int[] enu = tag.getIntArray("essType");
		int[] quant = tag.getIntArray("essQuant");
		if(enu.length==quant.length)
		{
			for(int i = 0; i<enu.length; i++)
			{
				EnumEssence type = EnumEssence.from(enu[i]);
				Essence ess = new Essence(quant[i], type);
				instance.addEssnece(ess);
			}
		}
		instance.setMax(tag.getInteger("maxEss"));
	}

}
