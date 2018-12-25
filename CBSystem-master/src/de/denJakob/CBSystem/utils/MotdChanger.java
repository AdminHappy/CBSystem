package de.denJakob.CBSystem.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import de.denJakob.CBSystem.main.Main;

public class MotdChanger implements Listener {
	
	@EventHandler
	public void onServerPing(ServerListPingEvent e) {
		e.setMotd(Main.getPlugin().getDatas().getString("Motd"));
	}

}
