package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class ChatclearCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.chatclear")) {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(!all.hasPermission("system.chatclear.bypass")) {
						for(int i = 0; i == 51; i++) {
							all.sendMessage(" ");
						}
					}
					all.sendMessage(Main.PREFIX_SYSTEM + "§7Der Chat wurde von §6" + p.getName() + " §7geleert.");
				}
				p.sendMessage(Main.PREFIX_SYSTEM + "§7Du hast den Chat geleert!");
			} else {
				p.sendMessage(Main.NO_PERMISSIONS_SYSTEM);
			}
		}
		return false;
	}

}
