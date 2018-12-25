package de.denJakob.CBSystem.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.denJakob.CBSystem.main.Main;

public class PlayerDeathListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		
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
		
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				p.teleport(loc);
			}
		}, 3);
	}

}
