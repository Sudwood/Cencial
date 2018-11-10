package com.sudwood.cencial;

import com.sudwood.cencial.container.ContainerBasicPlinth;
import com.sudwood.cencial.gui.GuiBasicPlinth;
import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
		case Reference.BasicPlinthGui:
			return new ContainerBasicPlinth(player.inventory, ((TileEntityBasicPlinth)world.getTileEntity(new BlockPos(x,y,z))), player);
	
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) 
	{
		switch(ID)
		{
		case Reference.BasicPlinthGui:
			return new GuiBasicPlinth(((TileEntityBasicPlinth)world.getTileEntity(new BlockPos(x,y,z))), new ContainerBasicPlinth(player.inventory, ((TileEntityBasicPlinth)world.getTileEntity(new BlockPos(x,y,z))), player));
	
		}
		return null;
	}

}
