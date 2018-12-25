package de.denJakob.CBSystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.listeners.PlayerAsyncChatListener;
import de.denJakob.CBSystem.main.Main;

public class BoldCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.bold")) {
				if(PlayerAsyncChatListener.bold.contains(p)) {
					PlayerAsyncChatListener.bold.remove(p);
					p.sendMessage(Main.PREFIX + "§7Du kannst nun nicht mehr dick schreiben");
				} else {
					PlayerAsyncChatListener.bold.add(p);
					p.sendMessage(Main.PREFIX + "§7Du kannst nun dick schreiben");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}

}
