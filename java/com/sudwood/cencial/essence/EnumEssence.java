package com.sudwood.cencial.essence;

import java.util.HashMap;
import java.util.Map;

public enum EnumEssence 
{
	HEAT(0),ENERGY(1),TERRA(2),AUQA(3)
	,DEATH(4),LIFE(5),UNHOLY(6),HOLY(7)
	,CORRUPTION(8),METAL(9),GREED(10),GLORY(11),CRYSTAL(12),DESTRUCTION(13),CREATION(14),AERO(15);
	public final int Value;
	private static final Map<Integer, EnumEssence> _map = new HashMap<Integer, EnumEssence>();
	static 
	{
		for(EnumEssence enume : EnumEssence.values())
		{
			_map.put(enume.Value, enume);
		}
	}
	
	public static EnumEssence from(int value)
	{
		return _map.get(value);
	}
	private EnumEssence(int value)
	{
		Value = value;
	}
	@Override
	public String toString()
	{
		switch(this)
		{
		case HEAT:
			return "Heat Essence";
		case ENERGY:
			return "Energy Essence";
		case TERRA:
			return "Terra Essence";
		case AUQA:
			return "Auqa Essence";
		case DEATH:
			return "Death Essence";
		case LIFE:
			return "Life Essence";
		case UNHOLY:
			return "Unholy Essence";
		case HOLY:
			return "Holy Essence";
		case CORRUPTION:
			return "Corruption Essence";
		case METAL:
			return "Metal Essence";
		case GREED:
			return "Greed Essence";
		case GLORY:
			return "Glory Essence";
		case CRYSTAL:
			return "Crystal Essence";
		case DESTRUCTION:
			return "Destruction Essence";
		case CREATION:
			return "Creation Essence";
		case AERO:
			return "Aero Essence";
		default:
			throw new IllegalArgumentException();
		}
	}
	public String toName()
	{
		switch(this)
		{
		case HEAT:
			return "Heat";
		case ENERGY:
			return "Energy";
		case TERRA:
			return "Terra";
		case AUQA:
			return "Auqa";
		case DEATH:
			return "Death";
		case LIFE:
			return "Life";
		case UNHOLY:
			return "Unholy";
		case HOLY:
			return "Holy";
		case CORRUPTION:
			return "Corruption";
		case METAL:
			return "Metal";
		case GREED:
			return "Greed";
		case GLORY:
			return "Glory";
		case CRYSTAL:
			return "Crystal";
		case DESTRUCTION:
			return "Destruction";
		case CREATION:
			return "Creation";
		case AERO:
			return "Aero";
		default:
			throw new IllegalArgumentException();
		}
	}
}
