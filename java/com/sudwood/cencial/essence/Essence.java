package com.sudwood.cencial.essence;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Default implementation of essence
 * @author shado
 *
 */
public class Essence implements IEssence
{
	private int numEssence = 0;
	private EnumEssence typeEssence;
	public Essence(int num, EnumEssence type)
	{
		numEssence = num;
		typeEssence = type;
	}
	
	public int consume(int points) 
	{
		if(points > numEssence)
		{
			numEssence = 0;
			return numEssence;
		}
		else 
			numEssence-=points;
		return points;
	}

	/**
	 * points to fill with
	 * do the fill or simulate - true is do - false is simulate
	 * returns amount filled
	 */
	public int fil(int points, boolean doFill) 
	{
		if(doFill)
		{
			
				numEssence+=points;
				return points;
		}
		if(!doFill)
		{
				return points;
		}
			return 0;
	}

	public void set(int points) 
	{
			numEssence = points;
		
	}

	public EnumEssence getType() 
	{
		return typeEssence;
	}

	public int getNum() {
		return numEssence;
	}




}
