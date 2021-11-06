package com.github.discordbotdev2.protcolus.listeners;

import com.github.discordbotdev2.protcolus.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;

public class asyncPlayerPreLoginListener implements Listener {
    private Main plugin;

    public asyncPlayerPreLoginListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        FileConfiguration config = plugin.getConfig();
        String playerName = event.getName();
        InetAddress ip = event.getAddress();
        config.set(playerName + "IP", ip);
        plugin.saveConfig();
    }
}
