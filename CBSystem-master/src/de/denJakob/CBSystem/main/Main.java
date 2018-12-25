package de.denJakob.CBSystem.main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.denJakob.CBSystem.commands.AbfallCommand;
import de.denJakob.CBSystem.commands.ArmorCommand;
import de.denJakob.CBSystem.commands.BoldCommand;
import de.denJakob.CBSystem.commands.BroadcastCommand;
import de.denJakob.CBSystem.commands.CBSystemCommand;
import de.denJakob.CBSystem.commands.ChatclearCommand;
import de.denJakob.CBSystem.commands.EventCommand;
import de.denJakob.CBSystem.commands.JaCommand;
import de.denJakob.CBSystem.commands.KopfCommand;
import de.denJakob.CBSystem.commands.NeinCommand;
import de.denJakob.CBSystem.commands.PingCommand;
import de.denJakob.CBSystem.commands.ReportCommand;
import de.denJakob.CBSystem.commands.SetCommand;
import de.denJakob.CBSystem.commands.SlowchatCommand;
import de.denJakob.CBSystem.commands.SpawnCommand;
import de.denJakob.CBSystem.commands.SpecCommand;
import de.denJakob.CBSystem.commands.StartkickCommand;
import de.denJakob.CBSystem.commands.TeamchatCommand;
import de.denJakob.CBSystem.commands.UnspecCommand;
import de.denJakob.CBSystem.config.Config;
import de.denJakob.CBSystem.config.Datas;
import de.denJakob.CBSystem.listeners.PlayerAsyncChatListener;
import de.denJakob.CBSystem.listeners.PlayerConnectionListener;
import de.denJakob.CBSystem.listeners.PlayerDeathListener;
import de.denJakob.CBSystem.listeners.WeatherChangeListener;
import de.denJakob.CBSystem.utils.DupePatch;
import de.denJakob.CBSystem.utils.EventAPI;
import de.denJakob.CBSystem.utils.KopfAPI;
import de.denJakob.CBSystem.utils.MySQL;
import de.denJakob.CBSystem.utils.ScoreboardManager;
import de.denJakob.CBSystem.utils.TitleAPI;
import de.denJakob.CBSystem.utils.Var;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public static Economy econ = null;
	
	public static String PREFIX = "§e•● §6CityBuild §8| §7";

	public static String NO_PERMISSIONS = Main.PREFIX + "§cDazu hast du keine Rechte!";

	public static String PREFIX_SYSTEM = "§eSystem §8| &7";

	public static String NO_PERMISSIONS_SYSTEM = Main.PREFIX_SYSTEM + "§cDazu hast du keine Rechte!";
	
	private Var var;
	private Datas datas;
	private Config config;
	private TitleAPI titleAPI;
	
	public void onEnable() {
		plugin = this;
		
		if (!setupEconomy() ) {
			getServer().getConsoleSender().sendMessage("Vault und/oder Essentials wurde/n nicht gefunden!");
        }
		
		File main = new File("plugins/CBSystem");
		if(!main.exists()) {
			main.mkdirs();
		}
		
		init();
        register();
		
		if(Main.getPlugin().getDatas().getBoolean("Scoreboard")) {
			getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
				
				@Override
				public void run() {
					for(Player all : Bukkit.getOnlinePlayers()) 
						ScoreboardManager.updateScoreboard(all);
				}
			}, 0, 5 * 20);
		}
	}
	
	public void onDisable() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer(Main.PREFIX + "§7Der Server startet neu!");
		}
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public void register() {
		// Commands
		getCommand("set").setExecutor(new SetCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("KopfCommand"))
			getCommand("kopf").setExecutor(new KopfCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("SpawnCommand"))
			getCommand("spawn").setExecutor(new SpawnCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("AbfallCommand"))
			getCommand("abfall").setExecutor(new AbfallCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("SpecCommand"))
			getCommand("spec").setExecutor(new SpecCommand());
			getCommand("unspec").setExecutor(new UnspecCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("TeamchatCommand"))
			getCommand("teamchat").setExecutor(new TeamchatCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("EventCommand"))
			getCommand("event").setExecutor(new EventCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("SlowchatCommand"))
			getCommand("slowchat").setExecutor(new SlowchatCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("Abstimmung"))
			getCommand("startkick").setExecutor(new StartkickCommand());
			getCommand("ja").setExecutor(new JaCommand());
			getCommand("nein").setExecutor(new NeinCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("PingCommand"))
			getCommand("ping").setExecutor(new PingCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("ReportCommand"))
			getCommand("report").setExecutor(new ReportCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("BroadcastCommand"))
			getCommand("broadcast").setExecutor(new BroadcastCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("ChatclearCommand"))
			getCommand("chatclear").setExecutor(new ChatclearCommand());
		getCommand("cbsystem").setExecutor(new CBSystemCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("ViewArmorCommand"))
			getCommand("armor").setExecutor(new ArmorCommand());
		if(Main.getPlugin().getDatas().getBooleans.get("BoldCommand"))
			getCommand("bold").setExecutor(new BoldCommand());
		
		// Listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new WeatherChangeListener(), this);
		pm.registerEvents(new PlayerConnectionListener(), this);
		if(Main.getPlugin().getDatas().getBooleans.get("Chat"))
			pm.registerEvents(new PlayerAsyncChatListener(), this);
		if(Main.getPlugin().getDatas().getBooleans.get("TPtoSpawnAfterDeath"))
			pm.registerEvents(new PlayerDeathListener(), this);
//		pm.registerEvents(new MotdChanger(), this);
		pm.registerEvents(new DupePatch(), this);
	}
	
	public void init() {
		setupMySQL();
		if(Main.getPlugin().getDatas().getBooleans.get("KopfCommand"))
			KopfAPI.enable();
		if(Main.getPlugin().getDatas().getBooleans.get("EventCommand"))
			EventAPI.enable();
		
		System.setProperty("file.encoding","UTF-8");
		Field charset = null;
		try {
			charset = Charset.class.getDeclaredField("defaultCharset");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		charset.setAccessible(true);
		try {
			charset.set(null,null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var = new Var();
        datas = new Datas();
        config = new Config();
        titleAPI = new TitleAPI();
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
        	if(getServer().getPluginManager().getPlugin("Vault").isEnabled()) {
        		try {
        			RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
                    if (rsp == null) {
                        return false;
                    }
                    econ = rsp.getProvider();
                    return econ != null;
				} catch (Exception e) {
					return false;
				}
        		
        	}
        }
    	return false;
    }
    
    public Var getVar() {
		return var;
	}
    
    private void setupMySQL() {
		
		File m = new File("plugins/CBSystem/MySQL.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(m);
		
		
		yml.addDefault("MySQL.user", "user");
		yml.addDefault("MySQL.host", "host");
		yml.addDefault("MySQL.db", "db");
		yml.addDefault("MySQL.pw", "pw");
		yml.addDefault("MySQL.port", "port");
		
		yml.options().copyDefaults(true);
		
		try {
			yml.save(m);
		} catch (IOException e) {
		}
		
		MySQL.connect(yml.getString("MySQL.host"), yml.getString("MySQL.db"), yml.getString("MySQL.user"),yml.getInt("MySQL.port"), yml.getString("MySQL.pw"));
	}
    
    public Datas getDatas() {
		return datas;
	}
    
    public Config getCostumConfig() {
		return config;
	}
    
    public TitleAPI getTitleAPI() {
		return titleAPI;
	}

}
