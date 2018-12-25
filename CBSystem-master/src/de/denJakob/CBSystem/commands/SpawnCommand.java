package de.denJakob.CBSystem.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			File file = new File("plugins/CBSystem/locs.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			String w = (String) cfg.getString("Spawn.world");
			double x = (double) cfg.getDouble("Spawn.x");
			double y = (double) cfg.getDouble("Spawn.y");
			double z = (double) cfg.getDouble("Spawn.z");
			float yaw = (float) cfg.getDouble("Spawn.yaw");
			float pitch = (float) cfg.getDouble("Spawn.pitch");
			
			World world = Bukkit.getWorld(w);
			Location loc = new Location(world, x, y, z, yaw, pitch);
			p.teleport(loc);
			
			p.sendMessage(Main.PREFIX + "§7Du wurdest zum Spawn teleportiert.");
		}
			
		return false;
	}

}
