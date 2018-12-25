package de.denJakob.CBSystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class NeinCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(StartkickCommand.startkick) {
				if(StartkickCommand.ja.contains(p) || StartkickCommand.nein.contains(p)) {
					p.sendMessage(Main.PREFIX + "§cDu hast bereits abgestimmt!");
					return false;
				} else {
					StartkickCommand.nein.add(p);
					p.sendMessage(Main.PREFIX + "§7Du hast für §cNEIN §7abgestimmt.");
				}
			} else {
				p.sendMessage(Main.PREFIX + "§cEs findet keine Abstimmung statt!");
			}
		}
		return false;
	}

}
