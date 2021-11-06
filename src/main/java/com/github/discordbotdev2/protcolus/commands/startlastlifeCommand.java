package com.github.discordbotdev2.protcolus.commands;

import com.github.discordbotdev2.protcolus.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class startlastlifeCommand implements CommandExecutor {
    private Main plugin;

    public startlastlifeCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("startlastlife").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcast("Starting Last Life in 1min.", null);
        return true;
    }
}
