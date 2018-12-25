package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.denJakob.CBSystem.main.Main;

public class ArmorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.armor")) {
				if(args.length == 1) {
					Player o = Bukkit.getPlayer(args[0]);
					if(o == null) {
						p.sendMessage(Main.PREFIX + "§cDiser Spieler ist nicht online!");
						return false;
					}
					Inventory inv = Bukkit.createInventory(null, 9 * 1, "§7Rüstung von " + o.getName());
					inv.setContents(o.getPlayer().getInventory().getArmorContents());
					p.openInventory(inv);
				} else {
					p.sendMessage(Main.PREFIX + "§cBitte verwende §e/armor <Spieler>§c!");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}

}
