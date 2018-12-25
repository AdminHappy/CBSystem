package de.denJakob.CBSystem.listeners;

import java.text.ParseException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.denJakob.CBSystem.commands.StartkickCommand;
import de.denJakob.CBSystem.utils.Var;

public class PlayerLoginListener implements Listener {
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if(StartkickCommand.banned.containsKey(e.getPlayer())) {
			try {
				if(Var.isCurrentTimeAfter(StartkickCommand.banned.get(e.getPlayer()))) {
					e.allow();
				} else {
					e.disallow(Result.KICK_BANNED, " \n §cDu wurdest für 10 Minuten rausgeworfen \n \n Um " + StartkickCommand.banned.get(e.getPlayer()) + "§7kannst du wieder joinen.");
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
				e.disallow(Result.KICK_BANNED, " \n §cDu wurdest für 10 Minuten rausgeworfen \n \n Um " + StartkickCommand.banned.get(e.getPlayer()) + "§7kannst du wieder joinen.");
			}
		}
	}

}
