package com.sudwood.cencial.essence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class EssenceHolder implements IEssenceHolder
{
	public HashMap<EnumEssence,Essence> essence = new HashMap<EnumEssence,Essence>();
	public ArrayList<EnumEssence> acceptTypes = new ArrayList<EnumEssence>();
	public int maxEss = 0;
	public EssenceHolder(EnumEssence[] accept, int max)
	{
		for(int i = 0; i<accept.length; i++)
		{
			acceptTypes.add(accept[i]);
		}
		maxEss = max;
	}
	/**
	 * returns true if added false if not
	 */
	public boolean addEssnece(Essence ess) 
	{
		if(!this.hasEssence(ess.getType()) && this.canAccpetType(ess.getType()))
		{
			essence.put(ess.getType(),ess);
			return true;
		}
		return false;
	}

	/**
	 * returns true if removed false if not
	 */
	public boolean removeEssenceType(EnumEssence en) 
	{
		if(this.hasEssence(en))
		{
			essence.remove(en);
		}
		return false;
	}

	public int consumeEssence(EnumEssence en, int points) 
	{
		if(this.hasEssence(en))
		{
			Essence temp = essence.get(en);
			if(temp.getNum() - points <= 0)
			{
				int result = temp.getNum();
				essence.remove(en);
				return result;
			}
			else
			{
				temp.consume(points);
				essence.put(en, temp);
				return points;
			}
			
		}
		return 0;
	}

	public int fillEssence(EnumEssence en, int points) 
	{
		if(this.hasEssence(en))
		{
			Essence temp = essence.get(en);
			if(temp.getNum()+points > maxEss)
			{
				int result = maxEss-temp.getNum();
				temp.set(maxEss);
				essence.put(en, temp);
				return result;
			}
			else
			{
				temp.set(temp.getNum()+points);
				essence.put(en, temp);
				return points;
			}
			
		}
		return 0;
	}

	public boolean hasEssence(EnumEssence en) 
	{
		return essence.containsKey(en);
	}

	public boolean canAccpetType(EnumEssence en) 
	{
		for(EnumEssence en1 : acceptTypes)
		{
			if(en1 == en)
			{
				return true;
			}
		}
		return false;
	}

	public HashMap getEssence() 
	{
		return essence;
	}

	public ArrayList getEssenceNames() 
	{
		ArrayList<String> output = new ArrayList<String>();
		for(EnumEssence en : essence.keySet())
		{
			output.add(en.toString());
		}
		return output;
	}

	public HashMap<String, Integer> getEssenceNamesAndNums()
	{
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		for(Entry<EnumEssence, Essence> pair : essence.entrySet())
		{
			output.put(pair.getKey().toString(), pair.getValue().getNum());
		}
		return output;
	}
	
	public void setMax(int max)
	{
		maxEss = max;
	}

}
