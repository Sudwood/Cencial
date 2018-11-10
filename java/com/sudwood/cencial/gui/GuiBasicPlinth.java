package com.sudwood.cencial.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import com.sudwood.cencial.CencialMod;
import com.sudwood.cencial.container.ContainerBasicPlinth;
import com.sudwood.cencial.essence.EssenceHolderProvider;
import com.sudwood.cencial.essence.IEssenceHolder;
import com.sudwood.cencial.tileentities.TileEntityBasicPlinth;

public class GuiBasicPlinth extends GuiContainer {
    public static final int WIDTH = 175;
    public static final int HEIGHT = 165;

    private static final ResourceLocation background = new ResourceLocation(CencialMod.MODID, "textures/gui/basicplinth.png");
    private static final ResourceLocation flame = new ResourceLocation(CencialMod.MODID, "textures/gui/flame.png");
    private static final ResourceLocation flameblank = new ResourceLocation(CencialMod.MODID, "textures/gui/blankflame.png");
    private TileEntityBasicPlinth tile;
    public GuiBasicPlinth(TileEntityBasicPlinth tileEntity, ContainerBasicPlinth container) 
    {
    	
        super(container);
        tile = tileEntity;
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
    {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        int l = tile.getBurnTime()/5;
        this.drawTexturedModalRect(guiLeft+ 40, guiTop+ 22, 176, 14, l + 1, 16);
        
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	if(GuiHelper.isInBox(mouseX, mouseY, guiLeft+74, guiLeft+103, guiTop+14, guiTop+44))
    	{
	    	IEssenceHolder ess = tile.getCapability(EssenceHolderProvider.ESS_CAP, null);
	    	if(ess!=null)
	    	{
		    	HashMap temp = ess.getEssenceNamesAndNums();
		    	ArrayList<String> result = new ArrayList<String>();
		    	Iterator entries = temp.entrySet().iterator();
				while (entries.hasNext()) 
				{
					Map.Entry entry = (Map.Entry) entries.next();
				    String en = (String)entry.getKey();
				    Integer ess2 = (Integer)entry.getValue();
					result.add(en+" : "+ess2);
				}
		    	 drawHoveringText(result, mouseX-174, mouseY-84);
	    	}
    	}
    	
    }
    
    
}