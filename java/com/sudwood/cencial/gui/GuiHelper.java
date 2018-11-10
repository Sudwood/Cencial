package com.sudwood.cencial.gui;

public class GuiHelper 
{
	public static boolean isInBox(int mouseX, int mouseY, int minX, int maxX, int minY, int maxY)
	{
		if(mouseX <= maxX && mouseX >=minX)
		{
			if(mouseY <=maxY && mouseY >=minY)
			{
				return true;
			}
		}
		return false;
	}
}
