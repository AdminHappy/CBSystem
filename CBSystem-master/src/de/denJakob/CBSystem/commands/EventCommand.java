package de.denJakob.CBSystem.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.denJakob.CBSystem.main.Main;
import de.denJakob.CBSystem.utils.EventAPI;
import de.denJakob.CBSystem.utils.ItemBuilder;
import de.denJakob.CBSystem.utils.ItemUtils;
import de.denJakob.CBSystem.utils.MySQL;
import net.milkbowl.vault.economy.Economy;

public class EventCommand implements CommandExecutor {
	
	static boolean event = false;
	static int counter = 10;
	int startCountdown;
	public static Economy eco = Main.econ;

	public ArrayList<Player> usedEvent = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("CBSystem.command.event")) {
				if(event == false) {
					if(!p.hasPermission("CBSystem.command.event.nodelay")) {
						
						if(!MySQL.isConnected()) {
							File m = new File("plugins/CBSystem/MySQL.yml");
							YamlConfiguration yml = YamlConfiguration.loadConfiguration(m);
							
							MySQL.connect(yml.getString("MySQL.host"), yml.getString("MySQL.db"), yml.getString("MySQL.user"),yml.getInt("MySQL.port"), yml.getString("MySQL.pw"));
							
						}
						
						
						EventAPI.JoinPlayer(p);
						if(EventAPI.getUsedEventPlayed(EventAPI.getUUID(p.getName())) == 0) {
							EventAPI.addUsedEventPlayed(p, 1);
							
						} else {
							p.sendMessage(Main.PREFIX + "§cDu nur ein Mal im Monat ein Event machen!");
							return false;
						}
					}
					
					
					p.sendMessage(Main.PREFIX + "§aDu hast das Event gestartet!");
					Bukkit.broadcastMessage(Main.PREFIX + "§6" + p.getName() + " §7hat das Event gestartet!");
					event = true;
					startCountdown = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							if(counter == 0) {
								Bukkit.broadcastMessage(Main.PREFIX + "§7Das Event startet §6jetzt!");
								counter--;
								startEvent(p);
								Bukkit.getScheduler().cancelTask(startCountdown);
							} else if(counter == 1) {
								Bukkit.broadcastMessage(Main.PREFIX + "§7Das Event startet in §6" + "einer" + " §7Sekunden.");
								counter--;
							} else if(counter == 10) {
								Bukkit.broadcastMessage(Main.PREFIX + "§7Das Event startet in §6" + counter + " §7Sekunden.");
								counter--;
							} else if(counter >= 6) {
								counter--;
							} else if(counter >= 2) {
								Bukkit.broadcastMessage(Main.PREFIX + "§7Das Event startet in §6" + counter + " §7Sekunden.");
								counter--;
							}
							
						}
					}, 0, 1 * 20);
				} else {
					p.sendMessage(Main.PREFIX + "§cEs ist bereits ein Event am laufen!");
				}
			} else {
				p.sendMessage(Main.NO_PERMISSIONS);
			}
		}
		return false;
	}
	
	@SuppressWarnings("static-access")
	public void startEvent(Player p) {
		Bukkit.broadcastMessage(Main.PREFIX + "§a§lDie Verlosung startet jetzt");
		event = false;
		counter = 10;
		for(Player all : Bukkit.getOnlinePlayers()) {
			int i = new Random().nextInt(100);
			if(i == 0) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.APPLE).name("§c§lErstes CB-Event").lore("§a§lErstes CB-Event").build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.APPLE).name("§c§lCB-Event").lore("§a§lCB-Event").build());
				}
			} else if(i <= 10) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.BEACON).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.BEACON).build());
				}				
			} else if(i == 11) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.BARRIER).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.BARRIER).build());
				}
			} else if(i <= 20) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.STONE).amount(64).build());
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.STONE).amount(64).build());
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.STONE).amount(64).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.STONE).amount(64).build());
					all.getInventory().addItem(new ItemBuilder(Material.STONE).amount(64).build());
					all.getInventory().addItem(new ItemBuilder(Material.STONE).amount(64).build());
				}
			} else if(i == 21) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + all.getName() + " 100000");
			} else if(i <= 30) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + all.getName() + " 1000");
			} else if(i == 31) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.CAKE).name("§c§lCB-Event").enchantment(Enchantment.DAMAGE_ALL, -1).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.CAKE).name("§c§lCB-Event").enchantment(Enchantment.DAMAGE_ALL, -1).build());
				}
			} else if(i <= 40) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.ANVIL).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.ANVIL).build());
				}
			} else if(i == 41) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemUtils().getHead("_miniluca_", "§eKopf von _miniluca_", "", 1));
				} else {
					all.getInventory().addItem(new ItemUtils().getHead("_miniluca_", "§eKopf von _miniluca_", "", 1));
				}
			} else if(i <= 50) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.COOKED_MUTTON).amount(64).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.COOKED_MUTTON).amount(64).build());
				}
			} else if(i == 51) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.DIRT).name("§c§lLeider nichts...").build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.DIRT).name("§c§lLeider nichts...").build());
				}
			} else if(i <= 60) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.DIRT).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.DIRT).build());
				}
			} else if(i == 61) {
				all.getInventory().addItem(new ItemBuilder(Material.BEDROCK).build());
			} else if(i <= 70) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.ANVIL).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.ANVIL).build());
				}
			} else if(i == 71) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemUtils().getHead("InfinityCrew", "§eKopf von InfinityCrew", "", 1));
				} else {
					all.getInventory().addItem(new ItemUtils().getHead("InfinityCrew", "§eKopf von InfinityCrew", "", 1));
				}
			} else if(i <= 80) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.DIRT).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.DIRT).build());
				}
			} else if(i == 81) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.CHAINMAIL_BOOTS).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.CHAINMAIL_BOOTS).build());
				}
			} else if(i <= 90) {
				if(isInventoryFull(all.getInventory())) {
					all.sendMessage(Main.PREFIX + "§7Da dein Inventar voll ist, wird das gewonnene Item gedroppt.");
					all.getLocation().getWorld().dropItem(all.getLocation(), new ItemBuilder(Material.DIRT).build());
				} else {
					all.getInventory().addItem(new ItemBuilder(Material.DIRT).build());
				}
			} else if(i == 91) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + all.getName() + " 100000");
			} else if(i <= 100) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + all.getName() + " 1000");
			}
		}
		
	}
	
	public static boolean isInventoryFull(Inventory inv) {
	     for (ItemStack i : inv.getContents()) {
		  if (i == null || i.getType() == Material.AIR) return false;
	     }
	     return true;
	}

}
