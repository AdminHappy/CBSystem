package de.denJakob.CBSystem.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.denJakob.CBSystem.commands.SlowchatCommand;
import de.denJakob.CBSystem.commands.StartkickCommand;
import de.denJakob.CBSystem.main.Main;

public class PlayerAsyncChatListener implements Listener {
	
	public static ArrayList<Player> bold = new ArrayList<>();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
	    String message = e.getMessage();
	    
	    Player p = e.getPlayer();
	    e.setCancelled(true);
	    
	    if(StartkickCommand.startkick) {
	    	if(!p.hasPermission("Chat.write.atStartkick")) {
	    		p.sendMessage(Main.PREFIX + "§cDu kannst während eines Startkick's nicht schreiben!");
	    		return;
	    	}
	    }
	    
	    if(SlowchatCommand.slowchat) {
	    	if(SlowchatCommand.usedChat.contains(p)) {
	    		p.sendMessage(Main.PREFIX + "§cDu kannst nur alle 3 Sekunden schreiben!");
	    		return;
	    	}
	    }
	    
	    if(p.hasPermission("Chat.write.Color")) {
    		message = ChatColor.translateAlternateColorCodes('&', message);
    	}
	    
	    if(bold.contains(p)) {
			message = "§l" + message;
		}
	    
	    if(message != null) {
		    if (p.hasPermission("Prefix.Inhaber")) {
		    	message = "§4Inhaber §7➜§4 " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Admin")) {
		    	message = "§cAdmin §7➜§7 " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.SrMod")) {
		    	message = "§cSrMod §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Mod")) {
		    	message = "§cMod §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Sup")) {
		    	message = "§9Sup §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Team")) {
		    	message = "§bTeam §7➜ " + p.getName() + "§8 | §7" + message;
			} else if (p.hasPermission("Prefix.T-Sup")) {
				message = "§9T-Sup §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Youtuber")) {
		    	message = "§5Youtuber §7➜ " + p.getName() + "§8 | §7" + message;
			} else if (p.hasPermission("Prefix.Kaiser")) {
				message = "§aKaiser §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Prinz")) {
				message = "§2Prinz §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Oberhaupt")) {
				message = "§1Oberhaupt §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Utope+")) {
				message = "§6Utope+ §7➜ " + p.getName() + "§8 | §7" + message;
		    } else if (p.hasPermission("Prefix.Utope")) {
				message = "§6Utope §7➜ " + p.getName() + "§8 | §7" + message;
		    } else {
		    	message = "§7Spieler ➜ " + p.getName() + "§8 | §7" + message;
		    }
	    } else {
	    	e.setCancelled(true);
	    }
	    if(p.hasPermission("Chat.has.strikes")) {
	    	Bukkit.broadcastMessage("§8»");
	    	Bukkit.broadcastMessage(message);
	    	Bukkit.broadcastMessage("§8»");
	    } else {
	    	Bukkit.broadcastMessage(message);
	    }
	    if(SlowchatCommand.slowchat) {
	    	if(!p.hasPermission("slowchat.ignore")) {
	    		SlowchatCommand.usedChat.add(p);
	    		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						SlowchatCommand.usedChat.remove(p);
					}
				}, 3 * 20);
	    	}
	    }
	}

}
