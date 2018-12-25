package de.denJakob.CBSystem.commands;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class StartkickCommand implements CommandExecutor {
	
	public static boolean startkick = false;
	
	static int counter = 15;
	int startCountdown;
	
	public ArrayList<Player> usedStartkick = new ArrayList<>();
	
	public static ArrayList<Player> ja = new ArrayList<>();
	public static ArrayList<Player> nein = new ArrayList<>();
	
	public static HashMap<Player, String> banned = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("cbsystem.command.startkick")) {
				if(!(args.length >= 2)) {
					p.sendMessage(Main.PREFIX + "§cBitte verwende §e/startkick <Spieler> <Nachricht>§c!");
					return false;
				}
				
				if(usedStartkick.contains(p)) {
					p.sendMessage(Main.PREFIX + "§cDu kannst diesen Befehl nur alle 30 Minuten benutzen!");
					return false;
				}
				
				if(startkick) {
					p.sendMessage(Main.PREFIX + "§cEs findet bereits ein Startkick statt!");
					return false;
				}
				
				Player o = Bukkit.getPlayer(args[0]);
				if(o == null) {
					p.sendMessage(Main.PREFIX + "§cDer Spieler ist nicht online!");
					return false;
				}
				
				if(o.equals(p)) {
					p.sendMessage(Main.PREFIX + "§cDu darfst dich nicht selber Startkicken!");
					return false;
				}
				
				if(o.hasPermission("cbsystem.command.startkick.cantbekicked")) {
					p.sendMessage(Main.PREFIX + "§c" + o.getName() + " kann nicht gekickt werden");
					return false;
				}
				
				if(!p.hasPermission("cbsystem.command.startkick.nodelay")) {
					usedStartkick.add(p);
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							usedStartkick.remove(p);
						}
					}, 30 * 60 * 20);
				}
				
				startkick = true;
				
				String msg = "";
				for(int i = 1; i < args.length; i++) {
					msg = msg + " " + args[i];
				}
				
				
				IChatBaseComponent chat = ChatSerializer.a("{text: \"[JA]\", color: \"green\", bold: \"true\", clickEvent: {\"action\": \"run_command\" , value: \"/ja \"}}");
				IChatBaseComponent chat2 = ChatSerializer.a("{text: \"[NEIN]\", color: \"red\", bold: \"true\", clickEvent: {\"action\": \"run_command\" , value: \"/nein \"}}");
				PacketPlayOutChat packet = new PacketPlayOutChat(chat);
				PacketPlayOutChat packet2 = new PacketPlayOutChat(chat2);
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.sendMessage(Main.PREFIX + "§6" + o.getName() + " §7wurde von §6" + p.getName() + " §7für:" + msg + " §7zum Startkick angemeldet.");
					
					((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
					((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet2);
				}
				
				ja.add(p);
				
				startCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						if(counter == 0) {
							
							startStartKick(o);
							Bukkit.getScheduler().cancelTask(startCountdown);
						} else if(counter == 1) {
							Bukkit.broadcastMessage(Main.PREFIX + "§7Der Startkick startet in §6" + "einer" + " §7Sekunden.");
						} else if(counter == 10) {
							Bukkit.broadcastMessage(Main.PREFIX + "§7Der Startkick startet in §6" + counter + " §7Sekunden.");
						} else if(counter == 15) {
							Bukkit.broadcastMessage(Main.PREFIX + "§7Der Startkick startet in §6" + counter + " §7Sekunden.");
						} else if(counter >= 6) {
							
						} else if(counter >= 2) {
							Bukkit.broadcastMessage(Main.PREFIX + "§7Der Startkick startet in §6" + counter + " §7Sekunden.");
						}
						counter--;
						
					}
				}, 0, 1 * 20);
				
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}
	
	public void startStartKick(Player p) {
		int Ja = ja.size();
		int Nein = nein.size();
		
		if(Ja > Nein) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + p.getName() + " 10m Die User auf dem Server haben dich für 10 Minuten ausgeschlossen.");
			Bukkit.broadcastMessage(Main.PREFIX + "§6" + p.getName() + " §7wurde für 10 Minuten ausgeschlossen!");
			Bukkit.broadcastMessage(Main.PREFIX + "§7Die Abstimmung ging §a" + Ja + "§7/§c" + Nein + " §7aus.");
			
//			DateFormat hhmmssFormat = new SimpleDateFormat("yyyyMMddhh:mm:ss");
//			Date now = new Date();
			
			DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
			
			
			banned.put(p, df.toString());
			
		} else {
			Bukkit.broadcastMessage(Main.PREFIX + "§6" + p.getName() + " §7wurde nicht gekickt!");
			Bukkit.broadcastMessage(Main.PREFIX + "§7Die Abstimmung ging §a" + Ja + "§7/§c" + Nein + " §7aus.");
		}
		
		startkick = false;
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(ja.contains(all)) {
				ja.remove(all);
			} else if(nein.contains(all)) {
				nein.remove(all);
			}
		}
		
		counter = 15;
	}

}
