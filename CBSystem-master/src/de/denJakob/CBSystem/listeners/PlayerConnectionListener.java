package de.denJakob.CBSystem.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.denJakob.CBSystem.commands.StartkickCommand;
import de.denJakob.CBSystem.main.Main;
import de.denJakob.CBSystem.utils.ScoreboardManager;

public class PlayerConnectionListener implements Listener {
	
	@EventHandler
	public void handlePlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);

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
		
		if(Main.getPlugin().getDatas().getBoolean("tablist")) {
			String header = Main.getPlugin().getDatas().getString("tabHeader").replace("%server%",
					Bukkit.getServerName());
			String footer = Main.getPlugin().getDatas().getString("tabFooter").replace("%server%",
					Bukkit.getServerName());
			
			for(Player all : Bukkit.getOnlinePlayers()) {
				Main.getPlugin().getTitleAPI().sendTablist(all, header, footer);
			}
		}
		
		ScoreboardManager.setScoreboard(p);
		for(Player all : Bukkit.getOnlinePlayers()) {
			ScoreboardManager.updateScoreboard(all);
		}
	}
	
	@EventHandler
	public void handlePlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		if(Main.getPlugin().getDatas().getBoolean("Scoreboard")) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				ScoreboardManager.updateScoreboard(all);
			}
		}
		
		if(StartkickCommand.startkick) {
			if(StartkickCommand.ja.contains(p)) {
				StartkickCommand.ja.remove(p);
			} else if(StartkickCommand.nein.contains(p)) {
				StartkickCommand.nein.remove(p);
			}
		}
	}

}
