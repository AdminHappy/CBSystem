package de.denJakob.CBSystem.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventAPI {
	
	public static void enable() {
		createTable();
	}
	
	private static void createTable() {
		MySQL.update("CREATE TABLE IF NOT EXISTS Event (usedEvent INT, UUID TEXT)");
	}
	
	public static void JoinPlayer(Player p) {
		File m = new File("plugins/CBSystem/MySQL.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(m);
		if(!MySQL.isConnected())
			MySQL.connect(yml.getString("MySQL.host"), yml.getString("MySQL.db"), yml.getString("MySQL.user"),yml.getInt("MySQL.port"), yml.getString("MySQL.pw"));
		if(!isPlayerexits(p.getUniqueId().toString())) {
			MySQL.update("INSERT INTO Event (usedEvent,UUID) VALUES ('" + 0 + "','" + p.getUniqueId().toString() + "')");		
			
			
		}
	}
	
	public static void addUsedEventPlayed(Player p, int usedEvent) {
		MySQL.update("UPDATE Event SET usedEvent = '" + Integer.valueOf(getUsedEventPlayed(p.getUniqueId().toString()) + usedEvent) + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
	}
	
	public static int getUsedEventPlayed(String UUID) {
		try {
			ResultSet rs = MySQL.getResult("SELECT * FROM Event WHERE UUID = '" + UUID + "'");
			if(rs.next()) {
				return rs.getInt("usedEvent");
			}
		} catch (SQLException e) {
		}
		return 0;
	}
	public static boolean isPlayerexits(String UUID) {
		try {
			ResultSet rs = MySQL.getResult("SELECT usedEvent FROM Event WHERE UUID = '" + UUID + "'");
			if(rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
		}
		
		return false;
	}
	
	
	  public static String getName( String uuid ) {	
			try {
				URL api = new URL( "https://mcapi.ca/name/uuid/" + uuid );
				BufferedReader reader = new BufferedReader( new InputStreamReader( api.openStream() ) );
				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(reader);
				JSONObject jsonobj = (JSONObject) obj;
				String name = ( String ) jsonobj.get( "name" );

				reader.close();
				return name;
			} catch ( Exception e ) {
				e.printStackTrace();
			}
			return null;
		}
	  @SuppressWarnings("deprecation")
	public static String getUUID( String name ) {	
            OfflinePlayer player = Bukkit.getOfflinePlayer(name);
			return player.getUniqueId().toString();
		}
}
