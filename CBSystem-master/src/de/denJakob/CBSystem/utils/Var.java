package de.denJakob.CBSystem.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Var {
	
	public ArrayList<Player> head = new ArrayList<>();
	
	public boolean isInventoryFull(Inventory inv) {
	     for (ItemStack i : inv.getContents()) {
		  if (i == null || i.getType() == Material.AIR) return false;
	     }
	     return true;
	}
	
	public static  boolean isCurrentTimeAfter(String afterhhmmss) throws ParseException {
		  DateFormat hhmmssFormat = new SimpleDateFormat("yyyyMMddhh:mm:ss");
		  Date now = new Date();
		  String yyyMMdd = hhmmssFormat.format(now).substring(0, 8);

		  return hhmmssFormat.parse(yyyMMdd+afterhhmmss).after(now);
	}
	
	

}
