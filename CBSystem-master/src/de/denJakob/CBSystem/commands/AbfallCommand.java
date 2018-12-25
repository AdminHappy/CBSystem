package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class AbfallCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("CBSystem.command.abfall")) {
				p.openInventory(Bukkit.createInventory(null, 18, "§cAbfall"));
				p.playSound(p.getLocation(), Sound.LAVA_POP, 2.0F, 1.0F);
			} else {
				p.sendMessage(Main.NO_PERMISSIONS_SYSTEM);
			}
		}
		return false;
	}

}
