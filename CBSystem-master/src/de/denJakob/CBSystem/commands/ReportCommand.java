package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class ReportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.report")) {
				if(args.length == 2) {
					Player o = Bukkit.getPlayer(args[0]);
					
					if(o == null) {
						p.sendMessage(Main.PREFIX_SYSTEM + "§cBitte gib einen Spieler, der online ist an!");
						return false;
					}
					
					if(!(args[1].equalsIgnoreCase("hacking") || 
							args[1].equalsIgnoreCase("dupen") ||
							args[1].equalsIgnoreCase("trolling") ||
							args[1].equalsIgnoreCase("beleidigen"))) {
						p.sendMessage(Main.PREFIX_SYSTEM + "§cBitte gib einen validen Grund an!");
						p.sendMessage("§cGründe:");
						p.sendMessage("- Hacking");
						p.sendMessage("- Dupen");
						p.sendMessage("- Trolling");
						p.sendMessage("- Beleidigen");
						return false;
					}
					
					p.sendMessage(Main.PREFIX_SYSTEM + "§aDer Spieler wurde erfolgreich gemeldet!");
					for(Player current : Bukkit.getOnlinePlayers()) {
						if(current.hasPermission("system.report.see")) {
							current.sendMessage("§7----- §aNEUER REPORT §7-----");
							current.sendMessage(" ");
							current.sendMessage("        §7Name: §6" + o.getName());
							current.sendMessage("        §7Grund: §6" + args[1]);
							current.sendMessage(" ");
							current.sendMessage("§7----- §a             §7-----");
						}
					}
				} else {
					p.sendMessage(Main.PREFIX_SYSTEM + "§cBitte verwende §e/report <Name> <Grund>§c!");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS_SYSTEM);
			}
		}
		return false;
	}

}
