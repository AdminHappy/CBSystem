package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class SpecCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("CBSystem.command.spec")) {
				if (args.length == 0) {
					p.sendMessage(Main.PREFIX_SYSTEM + "§7Gebe einen Spieler an");
					return false;
				}
				if (args.length == 1) {
					String arg = args[0];
					if (Bukkit.getPlayerExact(arg) == null) {
						p.sendMessage(Main.PREFIX_SYSTEM + "§7Dieser Spieler ist nicht online");
						return false;
					}
					Player target = Bukkit.getPlayerExact(arg);
					p.teleport(target.getLocation());
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage(Main.PREFIX_SYSTEM + "§7Du spectatest nun " + target.getDisplayName());
					
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							p.setGameMode(GameMode.SPECTATOR);
						}
					}, 5);
					return false;
				}
			} else {
				p.sendMessage(Main.PREFIX_SYSTEM);
			}
		}
		return false;
	}

}
