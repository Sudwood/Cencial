package com.sudwood.cencial.essence;

import java.util.ArrayList;
import java.util.HashMap;

public interface IEssenceHolder 
{
	public HashMap<EnumEssence,Essence> essence = new HashMap<EnumEssence,Essence>();
	public ArrayList<EnumEssence> acceptTypes = new ArrayList<EnumEssence>();
	public int maxEss = 0;
	public boolean addEssnece(Essence ess);
	public boolean removeEssenceType(EnumEssence en);
	public int consumeEssence(EnumEssence en, int points);
	public int fillEssence(EnumEssence en, int points);
	public boolean hasEssence(EnumEssence en);
	public boolean canAccpetType(EnumEssence en);
	public HashMap getEssence();
	public ArrayList getEssenceNames();
	public HashMap<String, Integer> getEssenceNamesAndNums();
	public void setMax(int max);
}
