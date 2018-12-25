package de.denJakob.CBSystem.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class SetCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("set")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("spawn.set")) {
					if(args.length == 0) {
						p.sendMessage(Main.PREFIX + "§cBitte benutze §6/set Spawn§c!");
					}else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("Spawn")) {
							File file = new File("plugins/CBSystem/locs.yml");
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							
							cfg.set("Spawn.world", p.getLocation().getWorld().getName());
							cfg.set("Spawn.x", p.getLocation().getX());
							cfg.set("Spawn.y", p.getLocation().getY());
							cfg.set("Spawn.z", p.getLocation().getZ());
							cfg.set("Spawn.xaw", p.getLocation().getYaw());
							cfg.set("Spawn.pitch", p.getLocation().getPitch());
							
							try {
								cfg.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							Main.getPlugin().reloadConfig();
							p.sendMessage(Main.PREFIX + "§eNeuer Spawn wurde erfolgreich gesetzt!");
						} else {
							p.sendMessage(Main.PREFIX + "§cBitte benutze §6/set Spawn§c!");
						}
					}else {
						p.sendMessage(Main.PREFIX + "§cBitte benutze §6/set Spawn§c!");
					}
				} else {
					p.sendMessage(Main.NO_PERMISSIONS);
				}
			}
		}
		
		return false;
	}

}
