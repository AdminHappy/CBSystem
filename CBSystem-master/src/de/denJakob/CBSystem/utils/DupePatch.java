package de.denJakob.CBSystem.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DupePatch implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (player.isOnline()) return;
        event.setCancelled(true);
    }
    
    public void onInteract(PlayerInteractEvent e) {
    	if(e.getItem().getAmount() <= 0) {
    		e.setCancelled(true);
    	}
    }
}
