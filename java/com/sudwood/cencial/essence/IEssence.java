package com.sudwood.cencial.essence;

import net.minecraft.nbt.NBTTagCompound;

public interface IEssence 
{
	public int consume(int points);
	public int fil(int points, boolean doFill);
	public void set(int points);
	public EnumEssence getType();
	public int getNum();

}
