package de.denJakob.CBSystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.denJakob.CBSystem.main.Main;
import net.milkbowl.vault.economy.Economy;

public class ScoreboardManager {
	
	public static Economy eco = Main.econ;
	
	@SuppressWarnings("deprecation")
	public static void setScoreboard(Player p) {
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = sb.getObjective("aaa");
		if(obj == null) {
			obj = sb.registerNewObjective("aaa", "bbb");
		}
		if(Main.getPlugin().getDatas().getBoolean("Scoreboard")) {
		if(Main.getPlugin().getDatas().getString("ScoreboardName") == null) {
			obj.setDisplayName("§6DeinServer§7.§6NET");
		} else {
			obj.setDisplayName(Main.getPlugin().getDatas().getString("ScoreboardName"));
		}
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		obj.getScore(updateTeam(sb, "Strike_1", "§8§M------------", "§8§M--", ChatColor.DARK_RED)).setScore(11);
		
		// Server
		obj.getScore(Bukkit.getOfflinePlayer("§8•§7● Server")).setScore(10);
		obj.getScore(updateTeam(sb, "Server", "§8➜ ", Bukkit.getServerName(), ChatColor.DARK_GREEN)).setScore(9);
		
		obj.getScore(Bukkit.getOfflinePlayer("§b ")).setScore(8);
		
		// Rang
		obj.getScore(updateTeam(sb, "Rang_1", "§8•§7● ", "§7Rang", ChatColor.GOLD)).setScore(7);
		if(p.hasPermission("Prefix.Owner")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§4Inhaber", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Admin")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§cAdmin", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.SrMod")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§cSrMod", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Mod")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§cMod", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Sup")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§9Sup", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Team")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§bTeam", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.TSup")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§9T-Sup", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Youtuber")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§5Youtuber", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Kaiser")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§aKaiser", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Prinz")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§2Prinz", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Oberhaupt")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§1Oberhaupt", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Utope+")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§6Utope+", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Utope")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§6Utope", ChatColor.BLUE)).setScore(6);
		} else {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§7Spieler", ChatColor.BLUE)).setScore(6);
		}
		
		obj.getScore(Bukkit.getOfflinePlayer("§e ")).setScore(5);
		
		// Economy
		
		
		//int money = (int) eco.getBalance(p);
		
		
		
		
		obj.getScore(updateTeam(sb, "Coins_1", "§8•§7● ", "§7Guthaben", ChatColor.AQUA)).setScore(4);
		if(eco == null) {
			obj.getScore(updateTeam(sb, "Coins_2", "§8➜ §anull", "§e", ChatColor.GREEN)).setScore(3);
		} else {
			int money = (int) eco.getBalance(p);
			obj.getScore(updateTeam(sb, "Coins_2", "§8➜ ", "" + money, ChatColor.GREEN)).setScore(3);
		}
		
		obj.getScore(updateTeam(sb, "Strike_2", "§8§M------------", "§8§M--", ChatColor.GRAY)).setScore(1);
		
		}
		
		
		Team owner = getTeam(sb, "0001Owner", "§4Inhaber §7➜§4 ", "");
		Team admin = getTeam(sb, "0002Admin", "§cAdmin §7➜ ", "");
		Team srmod = getTeam(sb, "0003SrMod", "§cSrMod §7➜ ", "");
		Team mod = getTeam(sb, "0004Mod", "§cMod §7➜ ", "");
		Team sup = getTeam(sb, "0005Sup", "§9Sup §7➜ ", "");
		Team team = getTeam(sb, "0006Team", "§bTeam §7➜ ", "");
		Team tsup = getTeam(sb, "0008TSup", "§9T-Sup §7➜ ", "");
		Team youtuber = getTeam(sb, "0009Youtuber", "§5Youtuber §7➜ ", "");
		Team kaiser = getTeam(sb, "0010Kaiser", "§aKaiser §7➜ ", "");
		Team prinz = getTeam(sb, "0011Prinz", "§2Prinz §7➜ ", "");
		Team oberhaupt = getTeam(sb, "0012Oberhaupt", "§1Oberhaupt §7➜ ", "");
		Team utopeplus = getTeam(sb, "0013Utope+", "§6Utope+ §7➜ ", "");
		Team utope = getTeam(sb, "0014Utope", "§6Utope §7➜ ", "");
		Team spieler = getTeam(sb, "0015Spieler", "§7Spieler ➜ ", "");
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.hasPermission("Prefix.Owner")) {
				owner.addPlayer(all);
			} else if(all.hasPermission("Prefix.Admin")) {
				admin.addPlayer(all);
			} else if(all.hasPermission("Prefix.SrMod")) {
				srmod.addPlayer(all);
			} else if(all.hasPermission("Prefix.Mod")) {
				mod.addPlayer(all);
			} else if(all.hasPermission("Prefix.Sup")) {
				sup.addPlayer(all);
			} else if(all.hasPermission("Prefix.Team")) {
				team.addPlayer(all);
			} else if(all.hasPermission("Prefix.TSup")) {
				tsup.addPlayer(all);
			} else if(all.hasPermission("Prefix.Youtuber")) {
				youtuber.addPlayer(all);
			} else if(all.hasPermission("Prefix.Kaiser")) {
				kaiser.addPlayer(all);
			} else if(all.hasPermission("Prefix.Prinz")) {
				prinz.addPlayer(all);
			} else if(all.hasPermission("Prefix.Oberhaupt")) {
				oberhaupt.addPlayer(all);
			} else if(all.hasPermission("Prefix.Utope+")) {
				utopeplus.addPlayer(all);
			} else if(all.hasPermission("Prefix.Utope")) {
				utope.addPlayer(all);
			} else {
				spieler.addPlayer(all);
			}
		}
		
		p.setScoreboard(sb);
	}
	
	@SuppressWarnings("deprecation")
	public static void updateScoreboard(Player p) {
		Scoreboard sb = p.getScoreboard();
		if(sb == null) {
			setScoreboard(p);
		}
		Objective obj = sb.getObjective("aaa");
		if(Main.getPlugin().getDatas().getBoolean("Scoreboard")) {
		
		//int money = (int) eco.getBalance(p);
		//Bukkit.broadcastMessage(money + "");
		
		// Rang
		if(p.hasPermission("Prefix.Owner")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§4Inhaber", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Admin")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§cAdmin", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.SrMod")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§cSrMod", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Mod")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§cMod", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Sup")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§9Sup", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Team")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§bTeam", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.TSup")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§9T-Sup", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Youtuber")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§5Youtuber", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Kaiser")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§aKaiser", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Prinz")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§2Prinz", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Oberhaupt")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§1Oberhaupt", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Utope+")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§6Utope+", ChatColor.BLUE)).setScore(6);
		} else if(p.hasPermission("Prefix.Utope")) {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§6Utope", ChatColor.BLUE)).setScore(6);
		} else {
			obj.getScore(updateTeam(sb, "Rang_2", "§8➜ ", "§7Spieler", ChatColor.BLUE)).setScore(6);
		}
		
		// Economy
		//int money = (int) eco.getBalance(p);
		//Bukkit.broadcastMessage(money + "");
		
		
		
		
		if(eco != null) {
			int money = (int) eco.getBalance(p);
			obj.getScore(updateTeam(sb, "Coins_2", "§8➜ ", "" + money, ChatColor.GREEN)).setScore(3);
		}
		
		}
			
		Team owner = getTeam(sb, "0001Owner", "§4Inhaber §7➜§4 ", "");
		Team admin = getTeam(sb, "0002Admin", "§cAdmin §7➜ ", "");
		Team srmod = getTeam(sb, "0003SrMod", "§cSrMod §7➜ ", "");
		Team mod = getTeam(sb, "0004Mod", "§cMod §7➜ ", "");
		Team sup = getTeam(sb, "0005Sup", "§9Sup §7➜ ", "");
		Team team = getTeam(sb, "0006Team", "§bTeam §7➜ ", "");
		Team tsup = getTeam(sb, "0008TSup", "§9T-Sup §7➜ ", "");
		Team youtuber = getTeam(sb, "0009Youtuber", "§5Youtuber §7➜ ", "");
		Team kaiser = getTeam(sb, "0010Kaiser", "§aKaiser §7➜ ", "");
		Team prinz = getTeam(sb, "0011Prinz", "§2Prinz §7➜ ", "");
		Team oberhaupt = getTeam(sb, "0012Oberhaupt", "§1Oberhaupt §7➜ ", "");
		Team utopeplus = getTeam(sb, "0013Utope+", "§6Utope+ §7➜ ", "");
		Team utope = getTeam(sb, "0014Utope", "§6Utope §7➜ ", "");
		Team spieler = getTeam(sb, "0015Spieler", "§7Spieler ➜ ", "");
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.hasPermission("Prefix.Owner")) {
				owner.addPlayer(all);
			} else if(all.hasPermission("Prefix.Admin")) {
				admin.addPlayer(all);
			} else if(all.hasPermission("Prefix.SrMod")) {
				srmod.addPlayer(all);
			} else if(all.hasPermission("Prefix.Mod")) {
				mod.addPlayer(all);
			} else if(all.hasPermission("Prefix.Sup")) {
				sup.addPlayer(all);
			} else if(all.hasPermission("Prefix.Team")) {
				team.addPlayer(all);
			} else if(all.hasPermission("Prefix.TSup")) {
				tsup.addPlayer(all);
			} else if(all.hasPermission("Prefix.Youtuber")) {
				youtuber.addPlayer(all);
			} else if(all.hasPermission("Prefix.Kaiser")) {
				kaiser.addPlayer(all);
			} else if(all.hasPermission("Prefix.Prinz")) {
				prinz.addPlayer(all);
			} else if(all.hasPermission("Prefix.Oberhaupt")) {
				oberhaupt.addPlayer(all);
			} else if(all.hasPermission("Prefix.Utope+")) {
				utopeplus.addPlayer(all);
			} else if(all.hasPermission("Prefix.Utope")) {
				utope.addPlayer(all);
			} else {
				spieler.addPlayer(all);
			}
		}
		
	}
	
	public static Team getTeam(Scoreboard sb, String Team, String prefix, String suffix) {
		Team team = sb.getTeam(Team);
		if(team == null) {
			team = sb.registerNewTeam(Team);
		}
		team.setPrefix(prefix);
		team.setSuffix(suffix);
		return team;
	}
	
	public static String updateTeam(Scoreboard sb, String Team, String prefix, String suffix, ChatColor entry) {
		Team team = sb.getTeam(Team);
		if(team == null) {
			team = sb.registerNewTeam(Team);
		}
		team.setPrefix(prefix);
		team.setSuffix(suffix);
		team.addEntry(entry.toString());
		return entry.toString();
	}

}
