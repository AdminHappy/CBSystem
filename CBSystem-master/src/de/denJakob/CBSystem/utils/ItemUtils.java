package de.denJakob.CBSystem.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtils {

	public static ItemStack getItem(Material material, String name, String lore, int damage, int amount) {
		ItemStack itemstack = new ItemStack(material, amount, (short) damage);
		ItemMeta meta = itemstack.getItemMeta();

		if (lore != null) {
			if (lore.contains("\n")) {
				ArrayList<String> lorelist = new ArrayList<>();
				String[] loresplit = lore.split("\n");

				for (String text : loresplit) {
					lorelist.add(text);
				}
				meta.setLore(lorelist);

			}else{
				meta.setLore(Arrays.asList(lore));
			}
		}
		meta.setDisplayName(name);
		itemstack.setItemMeta(meta);

		return itemstack;
	}
	
	public static ItemStack getHead(String PlayerName, String name, String lore, int amount) {
		ItemStack itemstack = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
		SkullMeta meta = (SkullMeta) itemstack.getItemMeta();

		if (lore != null) {
			if (lore.contains("\n")) {
				ArrayList<String> lorelist = new ArrayList<>();
				String[] loresplit = lore.split("\n");

				for (String text : loresplit) {
					lorelist.add(text);
				}
				meta.setLore(lorelist);

			}else{
				meta.setLore(Arrays.asList(lore));
			}
		}
		meta.setOwner(PlayerName);
		meta.setDisplayName(name);
		itemstack.setItemMeta(meta);

		return itemstack;
	}
	
	public static ItemStack getItemWithID(Integer ID, String name, String lore, int damage, int amount) {
		@SuppressWarnings("deprecation")
		ItemStack itemstack = new ItemStack(Material.getMaterial(ID), amount, (short) damage);
		ItemMeta meta = itemstack.getItemMeta();

		if (lore != null) {
			if (lore.contains("\n")) {
				ArrayList<String> lorelist = new ArrayList<>();
				String[] loresplit = lore.split("\n");

				for (String text : loresplit) {
					lorelist.add(text);
				}
				meta.setLore(lorelist);

			}else{
				meta.setLore(Arrays.asList(lore));
			}
		}
		meta.setDisplayName(name);
		itemstack.setItemMeta(meta);

		return itemstack;
	}
}
