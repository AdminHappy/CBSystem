package de.denJakob.CBSystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.denJakob.CBSystem.main.Main;

public class MySQL
{
	  public static Connection con;
	  
	  
  
	  public static void connect(String host, String db, String user, int port, String pw)
	  {
	    if (!isConnected()) {
	      try
	      {
	        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pw + "&useSSL=false");
	        System.out.println("[MySql] Verbindung wurde aufgebaut");
	      }
	      catch (SQLException e)
	      {
	      System.out.print("Die angegebene MySqlverbindung ist Falsch!");
	      Bukkit.getPluginManager().disablePlugin(Main.getPlugin());
	      }
	    }
	  }
  
  public static void disconnect()
  {
    if (isConnected()) {
      try
      {
        con.close();
        System.out.println("Verbindung wurde geschlossen");
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static boolean isConnected()
  {
    return con != null;
  }
  
  public static Connection getConnection()
  {
    return con;
  }
  

  public static ResultSet getResult(String qry)
  {
    try
    {
      return con.createStatement().executeQuery(qry);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  public static void update(String qry)
  {
    try
    {
      con.createStatement().executeUpdate(qry);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
