package com.sudwood.cencial.tileentities;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import com.sudwood.cencial.essence.EnumEssence;
import com.sudwood.cencial.essence.Essence;
import com.sudwood.cencial.essence.EssenceHolder;
import com.sudwood.cencial.essence.EssenceHolderProvider;
import com.sudwood.cencial.essence.IEssenceHolder;

public class TileEntityBasicPlinth extends TileEntity implements ITickable
{
      public static final int SIZE =2;
	private ItemStackHandler inventory = new ItemStackHandler(SIZE);
	private EssenceHolder essence = new EssenceHolder(new EnumEssence[]{EnumEssence.HEAT}, 16000);
	private int burnTime = 0;
	public static final int maxBurnTime = 100;
	public TileEntityBasicPlinth()
	{
		
	}
	public int getBurnTime() {
		return burnTime;
	}

	public void setBurnTime(int burnTime) {
		this.burnTime = burnTime;
	}

	public void readFromNBT(NBTTagCompound compound)
    {
		if(compound.getCompoundTag("Inventory")!=null)
		inventory.deserializeNBT((NBTTagCompound) compound.getCompoundTag("inventory"));
		burnTime = compound.getInteger("burnTime");
        super.readFromNBT(compound);
    }

	

	public void WriteToNBT(NBTTagCompound compound)
	{
		compound.setTag("inventory",inventory.serializeNBT());
		compound.setInteger("burnTime", burnTime);
		super.writeToNBT(compound);
	}

	public void update() 
	{
		if(inventory.getStackInSlot(0) == ItemStack.EMPTY)
		{
			burnTime = 0;
		}
		if(inventory.getStackInSlot(0) != ItemStack.EMPTY)
		{
			if(TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(0))!= 0)
			{
				burnTime++;
				if(burnTime>= maxBurnTime)
				{
					burnTime = 0;
					
					if(this.hasCapability(EssenceHolderProvider.ESS_CAP, null))
					{
						IEssenceHolder ess = this.getCapability(EssenceHolderProvider.ESS_CAP, null);
						if(ess.hasEssence(EnumEssence.HEAT))
						{
							ess.fillEssence(EnumEssence.HEAT, TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(0))/10);
							inventory.extractItem(0, 1, false);
						}
						else
						{
							ess.addEssnece(new Essence(TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(0))/10, EnumEssence.HEAT));
							inventory.extractItem(0, 1, false);
						}
					}
				}
			}
		}
		if(inventory.getStackInSlot(1) != ItemStack.EMPTY)
		{
			
		}
	}
	
	 @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing)
    {
	 return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == EssenceHolderProvider.ESS_CAP || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing)
    {
    	if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
    	{
    		return (T)inventory;
    	}
    	if(capability == EssenceHolderProvider.ESS_CAP)
    	{
    		return (T)essence;
    	}
    	else
    		return super.getCapability(capability, facing);
    }
    
   /* @Override
    public SPacketUpdateTileEntity getUpdatePacket() 
    {
        // Prepare a pa
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) 
    {
        this.readFromNBT(packet.getNbtCompound());
    }*/

		

}
