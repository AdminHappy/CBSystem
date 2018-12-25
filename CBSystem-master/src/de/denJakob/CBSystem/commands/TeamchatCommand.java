package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class TeamchatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("System.command.teamchat")) {
				if (args.length > 0) {
					String nachricht = "";
					for (int i = 0; i < args.length; i++) {
						nachricht = nachricht + " " + args[i];
					}
					for (Player p1 : Bukkit.getOnlinePlayers()) {
						if (p1.hasPermission("System.command.teamchat")) {
							p1.sendMessage("§eTeamchat §8» §7" + p.getName() + " §8| §7" + ChatColor.translateAlternateColorCodes('&', nachricht));
						}
					}
				} else {
					p.sendMessage(Main.PREFIX_SYSTEM + "§7Bitte gebe eine Nachricht an");
				}
			} else {
				p.sendMessage(Main.PREFIX_SYSTEM);
			}
		}
		return false;
	}

}
