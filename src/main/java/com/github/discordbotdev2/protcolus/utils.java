package com.github.discordbotdev2.protcolus;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Date;

public class utils {
    public static String color(String s) {return ChatColor.translateAlternateColorCodes('&', s);}
    static void banUser(String user, String reason, Date date) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(user, color(reason), date, null);
    }
}
