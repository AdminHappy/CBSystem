package de.denJakob.CBSystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class PingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.ping")) {
				if(args.length == 0) {
					p.sendMessage(Main.PREFIX_SYSTEM + "§7Dein Ping beträgt §e" + getPing(p) + "§7.");
				} else if(args.length == 1) {
					if(p.hasPermission("cbsystem.command.ping.other")) {
						Player o = Bukkit.getPlayer(args[0]);
						if(o == null) {
							p.sendMessage(Main.PREFIX_SYSTEM + "§cDer Spieler ist nicht online.");
							return false;
						}
						p.sendMessage(Main.PREFIX_SYSTEM + "§7Der Ping von §6" + o.getName() + " beträgt §e" + getPing(o) + "§7.");
					} else {
						p.sendMessage(Main.PREFIX_SYSTEM + "§7Dein Ping beträgt §e" + getPing(p) + "§7.");
					}
				} else {
					p.sendMessage(Main.PREFIX_SYSTEM + "§7Dein Ping beträgt §e" + getPing(p) + "§7.");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS_SYSTEM);
			}
		}
		return false;
	}
	
	public int getPing(Player p){
	    CraftPlayer pingc = (CraftPlayer)p;
	    EntityPlayer pinge = pingc.getHandle();
	    return pinge.ping;
	}

}
