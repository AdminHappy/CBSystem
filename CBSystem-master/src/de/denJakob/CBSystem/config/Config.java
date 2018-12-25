package de.denJakob.CBSystem.config;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import de.denJakob.CBSystem.main.Main;

public class Config {
	
	// File
	private File config = new File("plugins/CBSystem/", "config.yml");
	private File messages = new File("plugins/CBSystem/", "messages.yml");

	// FileConfiguration
	private FileConfiguration configcfg;
	private FileConfiguration messagescfg;

	public Config() {
		createConfigs();
		loadConfig();
		loadMessages();
	}

	private void createConfigs() {
		Main.getPlugin().saveDefaultConfig();
	
		if(!messages.exists()) {
			Main.getPlugin().saveResource("messages.yml", false);
		}
	}

	private void loadConfig() {
		configcfg = YamlConfiguration.loadConfiguration(config);

		try {
			Main.PREFIX = ChatColor.translateAlternateColorCodes('&', configcfg.getString("Prefix"));
			Main.PREFIX_SYSTEM = ChatColor.translateAlternateColorCodes('&', configcfg.getString("Prefix_System"));
			
			Main.getPlugin().getDatas().getStrings.put("Motd",
					ChatColor.translateAlternateColorCodes('&', configcfg.getString("Motd")));
			
			Main.getPlugin().getDatas().getBooleans.put("Scoreboard", configcfg.getBoolean("Scoreboard"));
			Main.getPlugin().getDatas().getStrings.put("ScoreboardName", ChatColor.translateAlternateColorCodes('&', configcfg.getString("ScoreboardName")));
			
			Main.getPlugin().getDatas().getBooleans.put("tablist", configcfg.getBoolean("tablist"));
			Main.getPlugin().getDatas().getStrings.put("tabHeader", ChatColor.translateAlternateColorCodes('&', configcfg.getString("tabHeader")));
			Main.getPlugin().getDatas().getStrings.put("tabFooter", ChatColor.translateAlternateColorCodes('&', configcfg.getString("tabFooter")));
			
			// Commands
			Main.getPlugin().getDatas().getBooleans.put("AbfallCommand", configcfg.getBoolean("AbfallCommand"));
			Main.getPlugin().getDatas().getBooleans.put("ViewArmorCommand", configcfg.getBoolean("ViewArmorCommand"));
			Main.getPlugin().getDatas().getBooleans.put("BoldCommand", configcfg.getBoolean("BoldCommand"));
			Main.getPlugin().getDatas().getBooleans.put("BroadcastCommand", configcfg.getBoolean("BroadcastCommand"));
			Main.getPlugin().getDatas().getBooleans.put("ChatclearCommand", configcfg.getBoolean("ChatclearCommand"));
			Main.getPlugin().getDatas().getBooleans.put("EventCommand", configcfg.getBoolean("EventCommand"));
			Main.getPlugin().getDatas().getBooleans.put("KopfCommand", configcfg.getBoolean("KopfCommand"));
			Main.getPlugin().getDatas().getBooleans.put("PingCommand", configcfg.getBoolean("PingCommand"));
			Main.getPlugin().getDatas().getBooleans.put("ReportCommand", configcfg.getBoolean("ReportCommand"));
			Main.getPlugin().getDatas().getBooleans.put("SlowchatCommand", configcfg.getBoolean("SlowchatCommand"));
			Main.getPlugin().getDatas().getBooleans.put("SpawnCommand", configcfg.getBoolean("SpawnCommand"));
			Main.getPlugin().getDatas().getBooleans.put("SpecCommand", configcfg.getBoolean("SpecCommand"));
			Main.getPlugin().getDatas().getBooleans.put("TeamchatCommand", configcfg.getBoolean("TeamchatCommand"));
			
			//Other
			Main.getPlugin().getDatas().getBooleans.put("Abstimmung", configcfg.getBoolean("Abstimmung"));
			Main.getPlugin().getDatas().getBooleans.put("Chat", configcfg.getBoolean("Chat"));
			Main.getPlugin().getDatas().getBooleans.put("TPtoSpawnAfterDeath", configcfg.getBoolean("TPtoSpawnAfterDeath"));
			
			System.out.println("loaded config.yml");
			
		} catch (Exception e) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Error in config.yml: " + e.getMessage());
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
		}

	}

	private void loadMessages() {
		messagescfg = YamlConfiguration.loadConfiguration(messages);

		try {
			Main.NO_PERMISSIONS = Main.PREFIX + ChatColor.translateAlternateColorCodes('&', messagescfg.getString("noPermission"));
			Main.NO_PERMISSIONS_SYSTEM = Main.PREFIX_SYSTEM + ChatColor.translateAlternateColorCodes('&', messagescfg.getString("noPermission"));
			
			System.out.println("loaded messages.yml");
			
		} catch (Exception e) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Error in messages.yml: " + e.getMessage());
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
		}
	}

}
