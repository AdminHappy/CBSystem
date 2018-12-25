package de.denJakob.CBSystem.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.denJakob.CBSystem.main.Main;
import de.denJakob.CBSystem.utils.ItemUtils;
import de.denJakob.CBSystem.utils.KopfAPI;
import de.denJakob.CBSystem.utils.MySQL;

public class KopfCommand implements CommandExecutor {

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("CBSystem.command.Kopf")) {
				if(args.length == 1) {
					if(p.hasPermission("CBSystem.command.kopf.nodelay")) {
						if(!MySQL.isConnected()) {
							File m = new File("plugins/CBSystem/MySQL.yml");
							YamlConfiguration yml = YamlConfiguration.loadConfiguration(m);
							
							MySQL.connect(yml.getString("MySQL.host"), yml.getString("MySQL.db"), yml.getString("MySQL.user"),yml.getInt("MySQL.port"), yml.getString("MySQL.pw"));
							
						}
						
						if(!Main.getPlugin().getVar().isInventoryFull(p.getInventory())) {
							p.getInventory().addItem(new ItemUtils().getHead(args[0], "§eKopf von " + args[0], null, 1));
						} else {
							p.sendMessage(Main.PREFIX + "§7Der Kopf wurde gedroppt, da ein Inventar voll ist.");
							p.getLocation().getWorld().dropItem(p.getLocation(), new ItemUtils().getHead(args[0], "§eKopf von " + args[0], null, 1));
						}
					} else {
						KopfAPI.JoinPlayer(p);
						if(KopfAPI.getUsedKopfPlayed(KopfAPI.getUUID(p.getName())) == 0) {
							KopfAPI.addUsedKopfPlayed(p, 1);
						} else {
							p.sendMessage(Main.PREFIX + "§cDu kannst nur 1 Mal pro Woche einen Kopf erhalten! ");
							return false;
						}
						
						if(!Main.getPlugin().getVar().isInventoryFull(p.getInventory())) {
							p.getInventory().addItem(new ItemUtils().getHead(args[0], "§eKopf von " + args[0], null, 1));
						} else {
							p.sendMessage(Main.PREFIX + "§7Der Kopf wurde gedroppt, da ein Inventar voll ist.");
							p.getLocation().getWorld().dropItem(p.getLocation(), new ItemUtils().getHead(args[0], "§eKopf von " + args[0], null, 1));
						}
					}					
				} else {
					p.sendMessage(Main.PREFIX + "§cBitte verwende §6/kopf <Spieler>§c!");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}
}
