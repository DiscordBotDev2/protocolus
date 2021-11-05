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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class firstJoinListener implements Listener {
    private Main plugin;

    public firstJoinListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @SuppressWarnings("deprecated")
    @EventHandler
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Random random = new Random();
        FileConfiguration config = plugin.getConfig();
        if (player.hasPlayedBefore()) {
            config.set(player.getName(),(int)Math.floor(Math.random()*(6-2+1)+2));
            plugin.saveConfig();
        }
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
            VeryGreen.addPlayer(player);
            VeryGreen.allowFriendlyFire();
            VeryGreen.setPrefix(ChatColor.DARK_GREEN+"");
            player.setDisplayName(ChatColor.DARK_GREEN+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 3) {
            Green.addPlayer(player);
            Green.allowFriendlyFire();
            Green.setPrefix(ChatColor.GREEN+"");
            player.setDisplayName(ChatColor.GREEN+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 2) {
            Yellow.addPlayer(player);
            Yellow.allowFriendlyFire();
            Yellow.setPrefix(ChatColor.YELLOW+"");
            player.setDisplayName(ChatColor.YELLOW+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 1) {
            Red.addPlayer(player);
            Red.allowFriendlyFire();
            Red.setPrefix(ChatColor.RED+"");
            player.setDisplayName(ChatColor.RED+player.getName());
            player.setPlayerListName(player.getDisplayName());
        }
        if (playerLives == 0) {
            BanList banList = new BanList() {
                @Override
                public BanEntry getBanEntry(String target) {
                    return null;
                }

                @Override
                public BanEntry addBan(String target, String reason, Date expires, String source) {
                    return null;
                }

                @Override
                public Set<BanEntry> getBanEntries() {
                    return null;
                }

                @Override
                public boolean isBanned(String target) {
                    return false;
                }

                @Override
                public void pardon(String target) {

                }
            };
            UUID uuid = player.getUniqueId();
            String suuid = uuid.toString();
            banList.addBan(suuid, "Lost all lives.",null ,null);
            player.kickPlayer("Lost all lives.");
        }
    }
}