package com.github.discordbotdev2.protcolus.listeners;

import com.github.discordbotdev2.protcolus.Main;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
@SuppressWarnings("deprecation")
public class deathListener implements Listener {
    private Main plugin;

    public deathListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        assert player != null;
        String playerName = player.getName();
        int lives;
        FileConfiguration config = plugin.getConfig();
        lives = (int) config.get(playerName) - 1;
        config.set(playerName, lives);
        plugin.saveConfig();
        ScoreboardManager sManager = new ScoreboardManager() {
            @Override
            public Scoreboard getMainScoreboard() {
                return null;
            }

            @Override
            public Scoreboard getNewScoreboard() {
                return null;
            }
        };
        Scoreboard board = sManager.getNewScoreboard();
        Team VeryGreen = board.registerNewTeam("VeryGreen");
        Team Green = board.registerNewTeam("Green");
        Team Yellow = board.registerNewTeam("Yellow");
        Team Red = board.registerNewTeam("Red");
        int playerLives = (int) config.get(player.getName());
        if (playerLives >= 4) {
            VeryGreen.removePlayer(player);
            Red.removePlayer(player);
            Green.removePlayer(player);
            Yellow.removePlayer(player);
            VeryGreen.addPlayer(player);
            VeryGreen.allowFriendlyFire();
            VeryGreen.setPrefix(ChatColor.DARK_GREEN+"");
            player.setDisplayName(ChatColor.DARK_GREEN+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 3) {
            VeryGreen.removePlayer(player);
            Red.removePlayer(player);
            Green.removePlayer(player);
            Yellow.removePlayer(player);
            Green.addPlayer(player);
            Green.allowFriendlyFire();
            Green.setPrefix(ChatColor.GREEN+"");
            player.setDisplayName(ChatColor.GREEN+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 2) {
            VeryGreen.removePlayer(player);
            Red.removePlayer(player);
            Green.removePlayer(player);
            Yellow.removePlayer(player);
            Yellow.addPlayer(player);
            Yellow.allowFriendlyFire();
            Yellow.setPrefix(ChatColor.YELLOW+"");
            player.setDisplayName(ChatColor.YELLOW+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 1) {
            VeryGreen.removePlayer(player);
            Red.removePlayer(player);
            Green.removePlayer(player);
            Yellow.removePlayer(player);
            Red.addPlayer(player);
            Red.allowFriendlyFire();
            Red.setPrefix(ChatColor.RED+"");
            player.setDisplayName(ChatColor.RED+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 0) {
            VeryGreen.removePlayer(player);
            Red.removePlayer(player);
            Green.removePlayer(player);
            Yellow.removePlayer(player);
            Bukkit.getBanList(BanList.Type.IP).addBan(playerName,"LOST ALL LIVES", null, null);
            player.kickPlayer("LOST ALL LIVES");
        }
    }

}