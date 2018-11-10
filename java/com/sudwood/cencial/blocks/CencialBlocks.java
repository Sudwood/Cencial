package com.sudwood.cencial.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.sudwood.cencial.CencialMod;

public class CencialBlocks 
{
	public static ArrayList<BaseBlock> BLOCKS = new ArrayList<BaseBlock>();
	
	public static BlockBasicPlinth basicPlinth = new BlockBasicPlinth("basicplinth");
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		basicPlinth.initModel();
		for(BaseBlock temp : BLOCKS)
		{
			temp.initModel();
		}
	}

}
