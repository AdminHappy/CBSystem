package de.denJakob.CBSystem.utils;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class TitleAPI {

	public void sendTitle(Player p, String tmsg, int stay) {
		IChatBaseComponent msg = ChatSerializer.a("{\"text\": \"\"}").a(tmsg);
		IChatBaseComponent smsg = ChatSerializer.a("{\"text\": \"\"}").a(" ");

		PacketPlayOutTitle time = new PacketPlayOutTitle(EnumTitleAction.TIMES, msg, 0, stay, 0);
		PacketPlayOutTitle time1 = new PacketPlayOutTitle(EnumTitleAction.TIMES, smsg, 0, stay, 0);
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, msg);
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, smsg);

		CraftPlayer pl = (CraftPlayer) p;

		pl.getHandle().playerConnection.sendPacket(time);
		pl.getHandle().playerConnection.sendPacket(time1);
		pl.getHandle().playerConnection.sendPacket(title);
		pl.getHandle().playerConnection.sendPacket(subtitle);
	}

	public void sendSubtitle(Player p, String submsg, int stay) {
		IChatBaseComponent msg = ChatSerializer.a("{\"text\": \"\"}").a(" ");
		IChatBaseComponent smsg = ChatSerializer.a("{\"text\": \"\"}").a(submsg);

		PacketPlayOutTitle time = new PacketPlayOutTitle(EnumTitleAction.TIMES, msg, 0, stay, 0);
		PacketPlayOutTitle time1 = new PacketPlayOutTitle(EnumTitleAction.TIMES, smsg, 0, stay, 0);
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, msg);
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, smsg);

		CraftPlayer pl = (CraftPlayer) p;

		pl.getHandle().playerConnection.sendPacket(time);
		pl.getHandle().playerConnection.sendPacket(time1);
		pl.getHandle().playerConnection.sendPacket(title);
		pl.getHandle().playerConnection.sendPacket(subtitle);
	}

	public void sendTitleandSubtitle(Player p, String tmsg, String submsg, int stay) {
		IChatBaseComponent msg = ChatSerializer.a("{\"text\": \"\"}").a(tmsg);
		IChatBaseComponent smsg = ChatSerializer.a("{\"text\": \"\"}").a(submsg);

		PacketPlayOutTitle time = new PacketPlayOutTitle(EnumTitleAction.TIMES, msg, 0, stay, 0);
		PacketPlayOutTitle time1 = new PacketPlayOutTitle(EnumTitleAction.TIMES, smsg, 0, stay, 0);
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, msg);
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, smsg);

		CraftPlayer pl = (CraftPlayer) p;

		pl.getHandle().playerConnection.sendPacket(time);
		pl.getHandle().playerConnection.sendPacket(time1);
		pl.getHandle().playerConnection.sendPacket(title);
		pl.getHandle().playerConnection.sendPacket(subtitle);
	}

	public void sendActionbar(Player p, String msg) {
		IChatBaseComponent amsg = ChatSerializer.a("{\"text\": \"\"}").a(msg);

		PacketPlayOutChat atitle = new PacketPlayOutChat(amsg, (byte) 2);

		CraftPlayer pl = (CraftPlayer) p;

		pl.getHandle().playerConnection.sendPacket(atitle);
	}

	public void sendActionbarAtAll(String msg) {
		IChatBaseComponent amsg = ChatSerializer.a("{\"text\": \"\"}").a(msg);

		PacketPlayOutChat atitle = new PacketPlayOutChat(amsg, (byte) 2);

		for (Player p : Bukkit.getOnlinePlayers()) {
			CraftPlayer pl = (CraftPlayer) p;

			pl.getHandle().playerConnection.sendPacket(atitle);
		}
	}

	public void sendTablist(Player p, String header, String footer) {
		PlayerConnection con = ((CraftPlayer) p).getHandle().playerConnection;

		IChatBaseComponent tabheader = ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent tabfooter = ChatSerializer.a("{\"text\": \"" + footer + "\"}");

		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(tabheader);
		try {
			Field f = packet.getClass().getDeclaredField("b");
			f.setAccessible(true);
			f.set(packet, tabfooter);
		} catch (Exception localException) {

		} finally {
			con.sendPacket(packet);
		}
	}

}