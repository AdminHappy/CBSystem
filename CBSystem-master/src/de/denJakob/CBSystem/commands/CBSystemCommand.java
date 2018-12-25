package de.denJakob.CBSystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class CBSystemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.cbsystem")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload") ||
							args[0].equalsIgnoreCase("rl")) {
						Main.getPlugin().reloadConfig();
						p.sendMessage(Main.PREFIX + "§aDie Config wurde neugeladen.");
					}
				} else {
					p.sendMessage(Main.PREFIX + "§cBitte verwende §e/cbsystem reload§c!");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}

}
