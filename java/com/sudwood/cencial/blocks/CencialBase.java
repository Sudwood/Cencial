package com.sudwood.cencial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CencialBase extends Block
{

	public CencialBase(Material materialIn) 
	{
		super(materialIn);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		
	}

}
