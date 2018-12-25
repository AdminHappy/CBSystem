package de.denJakob.CBSystem.config;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;

public class Datas {
	
	public HashMap<String, Integer> scoreboardLines = new HashMap<>();
	public HashMap<String, String> getStrings = new HashMap<>();
	public HashMap<String, Integer> getInteger = new HashMap<>();
	public HashMap<String, Double> getDoubles = new HashMap<>();
	public HashMap<String, Boolean> getBooleans = new HashMap<>();
	public HashMap<String, Sound> getSounds = new HashMap<>();
	public HashMap<String, Material> getMaterials = new HashMap<>();
	public HashMap<String, List<String>> getLists = new HashMap<>();
	public Inventory randInventory;

	public String getString(String s) {
		return getStrings.get(s);
	}

	public Integer getInteger(String s) {
		return getInteger.get(s);
	}

	public Double getDouble(String s) {
		return getDoubles.get(s);
	}

	public Boolean getBoolean(String s) {
		return getBooleans.get(s);
	}

	public Sound getSound(String s) {
		if (getSounds.containsKey(s)) {
			return getSounds.get(s);
		} else {
			return null;
		}
	}

	public Material getMaterial(String s) {
		return getMaterials.get(s);
	}

	public List<String> getStringList(String s) {
		return getLists.get(s);
	}

	public String booleanToString(boolean b) {
		if (b) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public Boolean stringToBoolean(String s) {
		if (s.equals("Yes")) {
			return true;
		} else {
			return false;
		}
	}
	
}