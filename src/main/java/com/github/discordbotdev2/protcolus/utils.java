package com.github.discordbotdev2.protcolus;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.util.Date;
import java.util.Objects;

public class utils {
    private static Main plugin;
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static void banUser(String user, String reason, Date date) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(user, color(reason), date, null);
    }
    public static void banIP(String user, String reason, Date date) {
        Player player = Bukkit.getPlayer(user);
        String playerName = player.getName();
        FileConfiguration config = plugin.getConfig();
        Bukkit.getBanList(BanList.Type.IP).addBan((String) Objects.requireNonNull(config.get(playerName + "IP")), color(reason), date, null);
    }

}
