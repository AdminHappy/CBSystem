package de.denJakob.CBSystem.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class SlowchatCommand implements CommandExecutor {
	
	public static boolean slowchat = false;
	
	public ArrayList<Player> usedSlowChat = new ArrayList<>();
	
	public static ArrayList<Player> usedChat = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.slowchat")) {
				if(usedSlowChat.contains(p)) {
					p.sendMessage(Main.PREFIX + "§cDu kannst diesen Befehl nur alle 30 Minuten benutzen!");
					return false;
				}
				
				if(slowchat) {
					slowchat = false;
					Bukkit.broadcastMessage(Main.PREFIX + "§7Der SlowChat wurde von §6" + p.getName() + " §7deaktiviert.");
					p.sendMessage(Main.PREFIX + "§7Du hast den SlowChat §6deaktiviert.");
				} else {
					slowchat = true;
					Bukkit.broadcastMessage(Main.PREFIX + "§7Der SlowChat wurde von §6" + p.getName() + " §7aktiviert.");
					p.sendMessage(Main.PREFIX + "§7Du hast den SlowChat §6aktiviert§7.");
				}
				
				if(!p.hasPermission("cbsystem.command.slowchat.nodelay")) {
					usedSlowChat.add(p);
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							usedSlowChat.remove(p);
						}
					}, 30 * 60 * 20);
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}

}
