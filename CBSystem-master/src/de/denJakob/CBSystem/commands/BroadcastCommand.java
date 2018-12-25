package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("cbsystem.command.broadcast")) {
				if(args.length == 0) {
					p.sendMessage(Main.PREFIX_SYSTEM + "§cBitte verwende §e/broadcast <Nachricht>§c!");
				} else {
					String message = "";
					for(int i = 1; i == args.length; i++) {
						message = message + args[i];
					}
					Bukkit.broadcastMessage("§cBroadcast§7➜ " + message);
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS_SYSTEM);
			}
		}
		return false;
	}

}
